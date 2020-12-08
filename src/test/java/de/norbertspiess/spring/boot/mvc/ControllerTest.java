package de.norbertspiess.spring.boot.mvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.JwtRequestPostProcessor;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.*;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpMethod.*;

@RunWith(SpringRunner.class)
public abstract class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper om;

    private JsonConverterService mapper;

    @PostConstruct
    public void init() {
        mapper = new JsonConverterService(om);
    }

    public <T> T mapResponseBodyToObject(ResultActions result, Class<T> target) throws IOException {
        return mapper.toObject(getResponseBody(result), target);
    }

    private String getResponseBody(ResultActions result) throws UnsupportedEncodingException {
        return result.andReturn().getResponse().getContentAsString();
    }

    public <T> List<T> mapResponseBodyToList(ResultActions result, Class<T> target) throws IOException {
        return mapper.toObjectList(getResponseBody(result), target);
    }

    protected JwtBuilder jwt(String role) {
        return new JwtBuilder().role(role);
    }

    protected RequestBuilder get(String path, Object... pathParameter) {
        return new RequestBuilder(GET, MessageFormat.format(path, pathParameter));
    }

    protected RequestBuilder post(String path, Object... pathParameter) {
        return new RequestBuilder(POST, MessageFormat.format(path, pathParameter));
    }

    protected RequestBuilder put(String path, Object... pathParameter) {
        return new RequestBuilder(PUT, MessageFormat.format(path, pathParameter));
    }

    protected RequestBuilder patch(String path, Object... pathParameter) {
        return new RequestBuilder(PATCH, MessageFormat.format(path, pathParameter));
    }

    protected RequestBuilder delete(String path, Object... pathParameter) {
        return new RequestBuilder(DELETE, MessageFormat.format(path, pathParameter));
    }

    protected class RequestBuilder {

        private final HttpMethod method;
        private final String path;
        private JwtRequestPostProcessor jwt;
        private Object body;
        private final Map<String, List<String>> parameters = new HashMap<>();

        private RequestBuilder(HttpMethod method, String path) {
            this.method = method;
            this.path = path;
        }

        public RequestBuilder parameter(String name, Object... values) {
            var strings = Arrays.stream(values).map(Object::toString).collect(toList());
            this.parameters.put(name, strings);
            return this;
        }

        /**
         * add a default jwt token
         */
        public RequestBuilder withJwt() {
            this.jwt = new JwtBuilder().build();
            return this;
        }

        public RequestBuilder withJwt(String role) {
            this.jwt = new JwtBuilder().role(role).build();
            return this;
        }

        public RequestBuilder withJwt(JwtRequestPostProcessor jwt) {
            this.jwt = jwt;
            return this;
        }

        public RequestBuilder body(Object body) {
            this.body = body;
            return this;
        }

        public ResultActions execute() throws Exception {
            MockHttpServletRequestBuilder request;
            switch (method) {
                case GET:
                    request = MockMvcRequestBuilders.get(path);
                    break;
                case POST:
                    request = MockMvcRequestBuilders.post(path);
                    break;
                case PUT:
                    request = MockMvcRequestBuilders.put(path);
                    break;
                case PATCH:
                    request = MockMvcRequestBuilders.patch(path);
                    break;
                case DELETE:
                    request = MockMvcRequestBuilders.delete(path);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + method);
            }

            request
                    .contentType(MediaType.APPLICATION_JSON)
                    .params(new LinkedMultiValueMap<>(parameters));

            addBodyIfSet(request);
            addJwtIfSet(request);

            return mockMvc.perform(request);
        }

        private void addBodyIfSet(MockHttpServletRequestBuilder request) throws JsonProcessingException {
            if (body != null) {
                if (body instanceof String) {
                    request.content(((String) body));
                } else {
                    request.content(mapper.toJson(body));
                }
            }
        }

        private void addJwtIfSet(MockHttpServletRequestBuilder request) {
            if (jwt != null) {
                request.with(jwt);
            }
        }
    }

    protected static class JwtBuilder {
        private String userId;
        private final Map<String, String> claims = new HashMap<>();
        private String userRole;

        public JwtBuilder() {
            userId(UUID.randomUUID().toString());
        }

        public JwtBuilder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public JwtBuilder role(String role) {
            this.userRole = role;
            return this;
        }

        public JwtRequestPostProcessor build() {
            var jwt = SecurityMockMvcRequestPostProcessors.jwt()
                    .jwt(builder -> {
                        builder.subject(userId);
                        claims.forEach(builder::claim);
                    });
            if (userRole != null) {
                jwt.authorities(new SimpleGrantedAuthority(userRole));
            }
            return jwt;
        }
    }
}
