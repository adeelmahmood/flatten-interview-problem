import java.io.IOException;

public class Flatten {

    public static void main(String[] args) throws IOException {
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