package PostKarat09June.CodeDesign.Router.Builder;

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

        return " 404 : Not Found";
    }


    static class WildCardRoute {


        String pattern;
        String result;

        WildCardRoute(String pattern, String result) {

            this.pattern = pattern;
            this.result = result;
        }

        public boolean matches(String path) {

            String regex = pattern.replace("*", ".*");
            return path.matches(regex);
        }

        public String getResult() {
            return result;
        }
    }


    static class RouteBuilder {

        Map<String, String> staticRoutes = new HashMap<>();
        List<WildCardRoute> wildCardRoutes = new ArrayList<>();

        RouteBuilder() {

        }

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


        Router router = new Router.RouteBuilder().withRoute("/baz","DELTA").withRoute("/play/*/go","ALPHA").build();


        System.out.println(router.callRoute("/baz"));
        System.out.println(router.callRoute("/play/4646/go"));
        System.out.println(router.callRoute("/playx/4646/test/go"));
    }
}
