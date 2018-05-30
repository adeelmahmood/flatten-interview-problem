import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Flatten {

    public static void main(String[] args) throws IOException {

        String json = "[\n" +
                "{\"a\":\n" +
                "  {\"b\":\n" +
                "    {\"c\":\n" +
                "      {\"d\":\"e\", \"d1\":{\"f\":\"g\"}}\n" +
                "    }\n" +
                "  },\n" +
                "\"a1\":\"b\"\n" +
                "},\n" +
                "{\"a\":{\"b\":{\"c\":{\"d\":{\"e\":\"f\"}}}}}\n" +
                "]";

        ObjectMapper m = new ObjectMapper();
        List<Map<String, Object>> data = m.readValue(json, List.class);

        for (Map<String, Object> d : data) {
            Map<String, String> flattenedData = flatten(d);
            System.out.println(flattenedData);
        }
    }

    public static Map<String, String> flatten(Map<String, Object> data) {
        Map<String, String> result = new HashMap<>();
        for (Map.Entry<String, Object> o : data.entrySet()) {

            if (o.getValue() instanceof Map) {
                Map<String, String> flatten = flatten((Map<String, Object>) o.getValue());

                for(Map.Entry<String, String> entry : flatten.entrySet()) {
                    result.put(o.getKey() + "_" + entry.getKey(), entry.getValue());
                }
            } else {
                result.put(o.getKey(), String.valueOf(o.getValue()));
            }
        }
        return result;
    }
}

/*
Write a function to flatten array of dicts:
example_input:
[
{"a":
  {"b":
    {"c":
      {"d":"e", "d1":{"f":"g"}}
    }
  },
"a1":"b"
},
{"a":{"b":{"c":{"d":{"e":"f"}}}}}
]
example_output:

[
{'a_b_c_d':'e'},
{'a_b_c_d1_f':'g'},
{'a1': 'b'},
{'a_b_c_d_e':'f'}
]

*/