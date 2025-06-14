package Post14June.Design.Router.Variables;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Router {


    Map<String, String> staticRoutes;
    List<WildCardRoute> wildCardRoutes;


    Router(RouteBuilder routeBuilder) {

        this.staticRoutes = routeBuilder.staticRoutes;
        this.wildCardRoutes = routeBuilder.wildCardRoutes;
    }


    public RouteResult callRoute(String path) {

        if (staticRoutes.containsKey(path)) {

            new RouteResult(path, Collections.emptyMap());
        } else {


            for (WildCardRoute wildCardRoute : wildCardRoutes) {


                Map<String, String> variables = wildCardRoute.matchAndGet(path);

                if (variables != null) {

                    return new RouteResult(path, variables);
                }
            }
        }

        return new RouteResult(" 404 : Not Found", Collections.emptyMap());
    }


    static class RouteResult {

        String result;
        Map<String, String> variables;

        public RouteResult(String result, Map<String, String> variables) {
            this.result = result;
            this.variables = new HashMap<>();
        }

        public String getResult() {
            return result;
        }

        public Map<String, String> getVariables() {
            return variables;
        }

        @Override
        public String toString() {
            return "RouteResult{" +
                    "result='" + result + '\'' +
                    ", variables=" + variables +
                    '}';
        }
    }


    static class WildCardRoute {


        String pattern;
        String result;
        List<String> variables;
        Integer specificity;
        Pattern regexPattern;


        WildCardRoute(String pattern, String result) {

            this.pattern = pattern;
            this.result = result;
            this.variables = new ArrayList<>();
            this.specificity = calculateSpecificity(pattern);
            this.regexPattern = generateRegexPattern(pattern);
        }


        private Pattern generateRegexPattern(String pattern) {


            String[] parts = pattern.split("/");
            StringBuilder sb = new StringBuilder();

            for (String part : parts) {

                if (part.isEmpty())
                    continue;

                sb.append("/");
                if (part.startsWith(":")) {

                    variables.add(part.substring(1));
                    sb.append("([^/]+)");
                } else {

                    sb.append(Pattern.quote(part));
                }
            }
            sb.append("/?$");
            return Pattern.compile(sb.toString());
        }


        public Map<String, String> matchAndGet(String pattern) {

            Matcher matcher = regexPattern.matcher(pattern);

            if (!matcher.matches()) {
                return null;
            }

            Map<String, String> result = new HashMap<>();

            for (int i = 0; i < variables.size(); i++) {

                result.put(variables.get(i), matcher.group(i + 1));
            }

            return result;
        }


        public String getResult() {
            return result;
        }

        public int getSpecificity() {
            return specificity;
        }

        public List<String> getVariables() {
            return variables;
        }


        private Integer calculateSpecificity(String pattern) {


            String[] segments = pattern.split("/");
            int specificity = 0;

            for (String segment : segments) {

                if (!segment.contains(":")) {

                    specificity++;
                }
            }

            return specificity;
        }
    }


    static class RouteBuilder {

        Map<String, String> staticRoutes = new HashMap<>();
        List<WildCardRoute> wildCardRoutes = new ArrayList<>();


        public RouteBuilder withRoute(String path, String result) {

            if (path.contains("*") || path.contains(":")) {

                wildCardRoutes.add(new WildCardRoute(path, result));
            } else {

                staticRoutes.put(path, result);
            }

            return this;
        }

        public Router build() {

            return new Router(this);
        }

    }


    public static void main(String[] args) {


        Router router = new Router.RouteBuilder()
                .withRoute("/foo/:id", "foo_variables").
                withRoute("accounts/:accountNumber/status", "accountStatus").build();

        System.out.println(router.callRoute("/foo/FGHF0047488"));
        System.out.println(router.callRoute("/accounts/85874873/status"));
    }
}
