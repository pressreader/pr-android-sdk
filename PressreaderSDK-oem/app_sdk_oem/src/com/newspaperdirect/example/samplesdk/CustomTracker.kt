package com.newspaperdirect.example.samplesdk

import android.util.Log
import com.newspaperdirect.sdk.analytics.ReadingViewAnalyticsTracker
import com.newspaperdirect.sdk.analytics.TrackingArticle
import com.newspaperdirect.sdk.analytics.TrackingIssue

class CustomTracker : ReadingViewAnalyticsTracker() {
    private val TAG = "CustomTracker"

    override fun trackOpenIssueForReading(issue: TrackingIssue) {
        Log.d(TAG, "OpenIssueForReading ${issue.cid} ${issue.title} ${issue.slug} ${issue.latestIssueDate} ${issue.analyticsName}")
    }

    override fun trackIssuePage(issue: TrackingIssue?, pageNumber: Int) {
        Log.d(TAG, "IssuePage $pageNumber ${issue?.cid} ${issue?.title} ${issue?.slug} ${issue?.latestIssueDate} ${issue?.analyticsName}")
    }

    override fun trackIssueTextFlow(issue: TrackingIssue) {
        Log.d(TAG, "IssueTextFlow ${issue.cid} ${issue.title} ${issue.slug} ${issue.latestIssueDate} ${issue.analyticsName}")
    }

    override fun trackArticleView(article: TrackingArticle) {
        Log.d(TAG, "ArticleView ${article.sourceSlug} ${article.headline} ${article.date}")
    }

    override fun trackTranslated(languageFrom: String, languageTo: String) {
        Log.d(TAG, "Translated $languageFrom $languageTo")
    }

    override fun trackListenView(issue: TrackingIssue) {
        Log.d(TAG, "ListenView ${issue.cid} ${issue.title} ${issue.slug} ${issue.latestIssueDate} ${issue.analyticsName}")
    }
}
