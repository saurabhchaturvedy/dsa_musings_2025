package Post14June.Design.Router.Conflict;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Router {


    Map<String, String> staticRoutes;
    List<WildCardRoute> wildCardRoutes;


    Router(RouteBuilder routeBuilder) {


        this.staticRoutes = routeBuilder.staticRoutes;
        this.wildCardRoutes = routeBuilder.wildCardRoutes;
    }


    public String callRoute(String path) {

        if (staticRoutes.containsKey(path)) {

            return staticRoutes.get(path);
        } else {

            for (WildCardRoute wildCardRoute : wildCardRoutes) {

                if (wildCardRoute.matches(path)) {

                    return wildCardRoute.getResult();
                }
            }
        }

        return "404 : Not Found";
    }


    static class WildCardRoute {

        String pattern;
        String result;
        Integer specificity;

        WildCardRoute(String pattern, String result) {

            this.pattern = pattern;
            this.result = result;
            this.specificity = calculateSpecificity(pattern);
        }

        public String getPattern() {
            return pattern;
        }

        public String getResult() {
            return result;
        }

        public Integer getSpecificity() {
            return specificity;
        }


        public boolean matches(String path) {

            String regex = pattern.replace("*", ".*");
            return path.matches(regex);
        }

        private Integer calculateSpecificity(String pattern) {

            String[] segments = pattern.split("/");
            int specificity = 0;

            for (String segment : segments) {

                if (!segment.contains("*")) {

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


            if (path.contains("*")) {

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


        Router router = new Router.RouteBuilder().withRoute("/user/profile", "profileResult").
                withRoute("/user/*", "wildCardRoute").build();


        System.out.println(router.callRoute("/user/profile"));
        System.out.println(router.callRoute("/user/any"));
    }
}
