package com.newspaperdirect.example.samplesdk

import android.app.Activity
import com.newspaperdirect.sdk.analytics.PressReaderAnalyticsTracker
import com.newspaperdirect.sdk.analytics.TrackingArticle
import com.newspaperdirect.sdk.analytics.TrackingIssue

internal class CustomTracker : PressReaderAnalyticsTracker() {

    override fun PR_Catalog(context: Activity, path: String) {}
    override fun PR_IssueOrder(context: Activity, trackingIssue: TrackingIssue) {}
    override fun PR_Settings(section: String) {}
    override fun PR_Accounts() {}
    override fun PR_Replica(context: Activity, trackingIssue: TrackingIssue) {}
    override fun PR_IssueFlow(context: Activity, trackingIssue: TrackingIssue) {}
    override fun PR_FeedFlow(context: Activity) {}
    override fun PR_ArticleText(context: Activity, article: TrackingArticle) {}
    override fun PR_Downloaded(context: Activity) {}
    override fun PR_Bookmarks(context: Activity, collection: String?) {}
    override fun PR_Search(context: Activity, term: String, contextName: String) {}
    override fun PR_SearchActivated(context: Activity, term: String) {}
    override fun PR_SearchDropdownClicked(context: Activity, type: String, term: String) {}
    override fun PR_Listen(context: Activity, trackingIssue: TrackingIssue) {}
    override fun PR_Comments(context: Activity, article: TrackingArticle) {}
    override fun PR_SigninForm() {}
    override fun PR_SignupForm() {}
    override fun PR_Login(method: String, service: String) {}
    override fun PR_SignUp(method: String) {}
    override fun PR_BillingInfoForm() {}
    override fun PR_AddPaymentInfo() {}
    override fun PR_IssueDateChanged(age: Int) {}
    override fun PR_FavoriteAdded(trackingIssue: TrackingIssue) {}
    override fun PR_FavoriteRemoved(trackingIssue: TrackingIssue) {}
    override fun PR_AutoDownloadSwitched(trackingIssue: TrackingIssue, subscribed: Boolean) {}
    override fun PR_Shared(content: String, title: String) {}
    override fun PR_Purchase(value: Double, currency: String) {}
    override fun PR_MainMenu(selection: String) {}
    override fun PR_AllPaymentOptions() {}
    override fun PR_PaymentViewItem(content: TrackingIssue?) {}
    override fun PR_PaymentViewItemList(content: TrackingIssue?) {}
    override fun PR_PaymentAddToCart(content: TrackingIssue?) {}
    override fun PR_Translate(from: String, to: String) {}
}
