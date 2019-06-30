package wh.start;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestWhatever {
    public static void main(String[] args) throws IOException {
//        testYml();
        TestWhatever t = new TestWhatever();
//        t.combine(5, 4);
        t.removeDuplicates(new int[] {1,1,1,2,3,3,4});
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    public static void testYml() throws IOException {

        FileInputStream inputStream = new FileInputStream(new File("src/main/resources/test.yml"));

        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory().enable(YAMLGenerator.Feature.MINIMIZE_QUOTES));
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        SequenceWriter sw = objectMapper.writer().writeValues(System.out);
        sw.write(jsonNode);
    }

    //Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        if (n <= 0 || k <= 0 || k > n) {
            return res;
        }
        List<Integer> combine = new ArrayList<>();
        findCombine(n, k, 1, combine);
        res.forEach(System.out::println);
        return res;
    }

    //    C(n,k)  回溯法

    private void findCombine(int n, int k, int index, List<Integer> combine) {
        if (k == combine.size()) {
            res.add(new ArrayList<>(combine));
            return;
        }
        // 还有k - c.size()个空位, 所以, [index...n] 中至少要有 k - c.size() 个元素
        // i最多为 n - (k - c.size()) + 1
        // 举例 C(5,4)
        while (index <= n - (k -combine.size()) +1) {
            combine.add(index);
            index++;
            findCombine(n, k, index, combine);
            combine.remove(combine.size() - 1);
        }

    }
}
