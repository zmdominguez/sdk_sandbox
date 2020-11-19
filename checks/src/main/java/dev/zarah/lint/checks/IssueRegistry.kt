package dev.zarah.lint.checks

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue

@Suppress("UnstableApiUsage")
class IssueRegistry : IssueRegistry() {
    override val issues: List<Issue> = listOf(
        TodoDetector.ISSUE
    )

    override val api = CURRENT_API
}