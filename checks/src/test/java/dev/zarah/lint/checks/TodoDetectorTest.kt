package dev.zarah.lint.checks

import com.android.tools.lint.checks.infrastructure.TestFiles
import com.android.tools.lint.checks.infrastructure.TestLintTask
import org.junit.Test

@Suppress("UnstableApiUsage")
class TodoDetectorTest {
    @Test
    fun testJavaFileNormalComment() {
        TestLintTask.lint()
            .files(
                TestFiles.java(
                    """
                package test.pkg;
                public class TestClass {
                    // In a comment, mentioning "lint" has no effect
                }
            """
                )
            )
            .issues(TodoDetector.ISSUE)
            .run()
            .expect("No warnings.")
    }

    @Test
    fun testKotlinFileNormalComment() {
        TestLintTask.lint()
            .files(
                TestFiles.kotlin(
                    """
                        package test.pkg
                        
                        class TestClass {
                            // In a comment, mentioning "lint" has no effect
                        }
                    """
                )
            )
            .issues(TodoDetector.ISSUE)
            .run()
            .expect("No warnings.")
    }

    @Test
    fun testJavaFileValidComment() {
        TestLintTask.lint()
            .files(
                TestFiles.java(
                    """
                package test.pkg;
                public class TestClass {
                    // TODO-Zarah (20200515): Some comments
                }
            """
                )
            )
            .issues(TodoDetector.ISSUE)
            .run()
            .expect("No warnings.")
    }

    @Test
    fun testKotlinFileValidComment() {
        TestLintTask.lint()
            .files(
                TestFiles.kotlin(
                    """
                        package test.pkg
                        class TestClass {
                            // TODO-Zarah (20200515): Some comments
                        }
                    """
                )
            )
            .issues(TodoDetector.ISSUE)
            .run()
            .expect("No warnings.")
    }

    @Test
    fun testJavaFileInvalidComment() {
        TestLintTask.lint()
            .files(
                TestFiles.java(
                    """
                package test.pkg;
                public class TestClass {
                    // TODO (20200515): Some comments
                }
            """
                )
            )
            .issues(TodoDetector.ISSUE)
            .run()
            .expect(
                """
                src/test/pkg/TestClass.java:4: Error: Please make sure to assign the TODO, include today's date in YYYYMMDD format, and the comment is properly formatted. [UnassignedTodo]
                                    // TODO (20200515): Some comments
                                    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                1 errors, 0 warnings
                """.trimIndent()
            )
    }

    @Test
    fun testKotlinFileInvalidComment() {
        TestLintTask.lint()
            .files(
                TestFiles.kotlin(
                    """
                package test.pkg
                class TestClass {
                    // TODO: Some comments
                }
            """
                )
            )
            .issues(TodoDetector.ISSUE)
            .run()
            .expect(
                """
                src/test/pkg/TestClass.kt:4: Error: Please make sure to assign the TODO, include today's date in YYYYMMDD format, and the comment is properly formatted. [UnassignedTodo]
                                    // TODO: Some comments
                                    ~~~~~~~~~~~~~~~~~~~~~~
                1 errors, 0 warnings
                """.trimIndent()
            )
    }

    @Test
    fun testKotlinFileDateFormat() {
        TestLintTask.lint()
            .files(
                TestFiles.kotlin(
                    """
                package test.pkg
                class TestClass {
                    // TODO-Zarah (30 Sep. 2020): Some comments
                }
            """
                )
            )
            .issues(TodoDetector.ISSUE)
            .run()
            .expect(
                """
                src/test/pkg/TestClass.kt:4: Error: Please make sure to assign the TODO, include today's date in YYYYMMDD format, and the comment is properly formatted. [UnassignedTodo]
                                    // TODO-Zarah (30 Sep. 2020): Some comments
                                    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                1 errors, 0 warnings
                """.trimIndent()
            )
    }

    @Test
    fun testKotlinFileLowercaseComment() {
        TestLintTask.lint()
            .files(
                TestFiles.kotlin(
                    """
                package test.pkg
                class TestClass {
                    // todo Some comments
                }
            """
                )
            )
            .issues(TodoDetector.ISSUE)
            .run()
            .expect(
                """
                src/test/pkg/TestClass.kt:4: Error: Please make sure to assign the TODO, include today's date in YYYYMMDD format, and the comment is properly formatted. [UnassignedTodo]
                                    // todo Some comments
                                    ~~~~~~~~~~~~~~~~~~~~~
                1 errors, 0 warnings
                """.trimIndent()
            )
    }

    @Test
    fun testJavaFileLowercaseComment() {
        TestLintTask.lint()
            .files(
                TestFiles.java(
                    """
                package test.pkg;
                
                public class TestClass {
                    // todo Some comments
                }
            """
                )
            )
            .issues(TodoDetector.ISSUE)
            .run()
            .expect(
                """
                src/test/pkg/TestClass.java:5: Error: Please make sure to assign the TODO, include today's date in YYYYMMDD format, and the comment is properly formatted. [UnassignedTodo]
                                    // todo Some comments
                                    ~~~~~~~~~~~~~~~~~~~~~
                1 errors, 0 warnings
                """.trimIndent()
            )
    }
}