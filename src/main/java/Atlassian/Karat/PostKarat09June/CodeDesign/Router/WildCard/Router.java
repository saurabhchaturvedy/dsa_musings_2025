package PostKarat09June.CodeDesign.Router.WildCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Router {


    Map<String, String> staticRoutes;
    List<WildCardRoute> wildCardRoutes;


    Router() {
        this.staticRoutes = new HashMap<>();
        this.wildCardRoutes = new ArrayList<>();

    }


    public void withRoute(String path, String result) {

        if (path.contains("*")) {
            wildCardRoutes.add(new WildCardRoute(path, result));

        } else {
            staticRoutes.put(path, result);

        }
    }


    public String callRoute(String path) {

        if (staticRoutes.containsKey(path)) {

            return staticRoutes.get(path);
        }

        for (WildCardRoute wildCardRoute : wildCardRoutes) {

            if (wildCardRoute.matches(path)) {

                return wildCardRoute.getResult();
            }
        }

        return "404: Not Found";
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


    public static void main(String[] args) {


        Router router = new Router();

        router.withRoute("/bar","fooooooo");
        router.withRoute("/play/*/go","wildcarddddd");


        System.out.println(router.callRoute("/play/test/go"));
        System.out.println(router.callRoute("/bar"));


    }
}
