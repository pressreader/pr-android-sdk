package com.newspaperdirect.example.samplesdk

import com.newspaperdirect.sdk.analytics.ReadingViewAnalyticsTracker
import com.newspaperdirect.sdk.analytics.TrackingArticle
import com.newspaperdirect.sdk.analytics.TrackingIssue
import timber.log.Timber

class CustomTracker : ReadingViewAnalyticsTracker() {

    private val TAG = "CustomTracker"

    override fun trackOpenIssueForReading(issue: TrackingIssue) {
        Timber.tag(TAG).d("OpenIssueForReading ${issue.cid} ${issue.title} ${issue.slug} ${issue.latestIssueDate} ${issue.analyticsName}")
    }

    override fun trackIssuePage(issue: TrackingIssue?, pageNumber: Int) {
        Timber.tag(TAG).d("IssuePage $pageNumber ${issue?.cid} ${issue?.title} ${issue?.slug} ${issue?.latestIssueDate} ${issue?.analyticsName}")
    }

    override fun trackIssueTextFlow(issue: TrackingIssue) {
        Timber.tag(TAG).d("IssueTextFlow ${issue.cid} ${issue.title} ${issue.slug} ${issue.latestIssueDate} ${issue.analyticsName}")
    }

    override fun trackArticleView(article: TrackingArticle) {
        Timber.tag(TAG).d("ArticleView ${article.sourceSlug} ${article.headline} ${article.date}")
    }

    override fun trackTranslated(languageFrom: String, languageTo: String) {
        Timber.tag(TAG).d("Translated $languageFrom $languageTo")
    }

    override fun trackListenView(issue: TrackingIssue) {
        Timber.tag(TAG).d("ListenView ${issue.cid} ${issue.title} ${issue.slug} ${issue.latestIssueDate} ${issue.analyticsName}")
    }
}
