package PostKarat09June.CodeDesign.Router.Basic;

import java.util.HashMap;
import java.util.Map;

public class Router {


    Map<String, String> map;


    Router() {

        this.map = new HashMap<>();
    }


    public void withRoute(String path, String result) {

        map.put(path, result);
    }


    public String callRoute(String path) {

        if (map.containsKey(path)) {
            return map.get(path);
        } else {

            return "404: Not Found";
        }
    }

    public static void main(String[] args) {


        Router router = new Router();

        router.withRoute("/bar", "foooo");
        router.withRoute("/baz", "coool");

        System.out.println(router.callRoute("/baz"));

    }
}
