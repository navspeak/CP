/*
Interviewbit:
Given an absolute path for a file (Unix-style), simplify it.
Examples:
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"
Note that absolute path always begin with ‘/’ ( root directory )
Path will not have whitespace characters.
 */
package stacks;
public class SimplifyDirectorySearch {

    public String simplifyPath(String A) {
    return null;
    }

}


class SimplifyDirSearchTest{
    public static void main(String[] args) {
        SimplifyDirectorySearch test = new SimplifyDirectorySearch();
        System.out.println(
                test.simplifyPath("/home").equals("/home") ?
                        "Test 1 Passed" : "Test 1 Failed");
        System.out.println(
                test.simplifyPath("/home/").equals("/home") ?
                        "Test 2 Passed" : "Test 2 Failed");
        System.out.println(
                test.simplifyPath("/home/..").equals("/") ?
                        "Test 3 Passed" : "Test 3 Failed");
        System.out.println(
                test.simplifyPath("/home/../a/./b").equals("/a/b") ?
                        "Test 4 Passed" : "Test 4 Failed");
        System.out.println(
                test.simplifyPath("/a/./b/../../c/").equals("/c") ?
                        "Test 5 Passed" : "Test 5 Failed");
    }
}