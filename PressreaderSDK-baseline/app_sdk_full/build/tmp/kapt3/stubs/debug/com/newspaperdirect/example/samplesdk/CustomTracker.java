package com.newspaperdirect.example.samplesdk;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u0006\n\u0002\b\u0013\b\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0004H\u0016J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0004H\u0016J\u001a\u0010\u0012\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u0018\u0010\u0015\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u0014H\u0016J\u0018\u0010\u0017\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\u0018\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\u0019\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u001a\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u001b\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0018\u0010\u001f\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010 \u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010!\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u00142\u0006\u0010$\u001a\u00020\u0014H\u0016J\u0010\u0010%\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\u0014H\u0016J\u0012\u0010\'\u001a\u00020\u00042\b\u0010(\u001a\u0004\u0018\u00010\u000eH\u0016J\u0012\u0010)\u001a\u00020\u00042\b\u0010(\u001a\u0004\u0018\u00010\u000eH\u0016J\u0012\u0010*\u001a\u00020\u00042\b\u0010(\u001a\u0004\u0018\u00010\u000eH\u0016J\u0018\u0010+\u001a\u00020\u00042\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u0014H\u0016J\u0018\u0010/\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH\u0016J \u00100\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u00101\u001a\u00020\u00142\u0006\u00102\u001a\u00020\u0014H\u0016J\u0018\u00103\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u00101\u001a\u00020\u0014H\u0016J \u00104\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u00105\u001a\u00020\u00142\u0006\u00101\u001a\u00020\u0014H\u0016J\u0010\u00106\u001a\u00020\u00042\u0006\u00107\u001a\u00020\u0014H\u0016J\u0018\u00108\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u00142\u0006\u00109\u001a\u00020\u0014H\u0016J\u0010\u0010:\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u0014H\u0016J\b\u0010;\u001a\u00020\u0004H\u0016J\b\u0010<\u001a\u00020\u0004H\u0016J\u0018\u0010=\u001a\u00020\u00042\u0006\u0010>\u001a\u00020\u00142\u0006\u0010?\u001a\u00020\u0014H\u0016\u00a8\u0006@"}, d2 = {"Lcom/newspaperdirect/example/samplesdk/CustomTracker;", "Lcom/newspaperdirect/sdk/analytics/PressReaderAnalyticsTracker;", "()V", "PR_Accounts", "", "PR_AddPaymentInfo", "PR_AllPaymentOptions", "PR_ArticleText", "context", "Landroid/app/Activity;", "article", "Lcom/newspaperdirect/sdk/analytics/TrackingArticle;", "PR_AutoDownloadSwitched", "trackingIssue", "Lcom/newspaperdirect/sdk/analytics/TrackingIssue;", "subscribed", "", "PR_BillingInfoForm", "PR_Bookmarks", "collection", "", "PR_Catalog", "path", "PR_Comments", "PR_Downloaded", "PR_FavoriteAdded", "PR_FavoriteRemoved", "PR_FeedFlow", "PR_IssueDateChanged", "age", "", "PR_IssueFlow", "PR_IssueOrder", "PR_Listen", "PR_Login", "method", "service", "PR_MainMenu", "selection", "PR_PaymentAddToCart", "content", "PR_PaymentViewItem", "PR_PaymentViewItemList", "PR_Purchase", "value", "", "currency", "PR_Replica", "PR_Search", "term", "contextName", "PR_SearchActivated", "PR_SearchDropdownClicked", "type", "PR_Settings", "section", "PR_Shared", "title", "PR_SignUp", "PR_SigninForm", "PR_SignupForm", "PR_Translate", "from", "to", "app_sdk_full_debug"})
public final class CustomTracker extends com.newspaperdirect.sdk.analytics.PressReaderAnalyticsTracker {
    
    public CustomTracker() {
        super();
    }
    
    @java.lang.Override()
    public void PR_Catalog(@org.jetbrains.annotations.NotNull()
    android.app.Activity context, @org.jetbrains.annotations.NotNull()
    java.lang.String path) {
    }
    
    @java.lang.Override()
    public void PR_IssueOrder(@org.jetbrains.annotations.NotNull()
    android.app.Activity context, @org.jetbrains.annotations.NotNull()
    com.newspaperdirect.sdk.analytics.TrackingIssue trackingIssue) {
    }
    
    @java.lang.Override()
    public void PR_Settings(@org.jetbrains.annotations.NotNull()
    java.lang.String section) {
    }
    
    @java.lang.Override()
    public void PR_Accounts() {
    }
    
    @java.lang.Override()
    public void PR_Replica(@org.jetbrains.annotations.NotNull()
    android.app.Activity context, @org.jetbrains.annotations.NotNull()
    com.newspaperdirect.sdk.analytics.TrackingIssue trackingIssue) {
    }
    
    @java.lang.Override()
    public void PR_IssueFlow(@org.jetbrains.annotations.NotNull()
    android.app.Activity context, @org.jetbrains.annotations.NotNull()
    com.newspaperdirect.sdk.analytics.TrackingIssue trackingIssue) {
    }
    
    @java.lang.Override()
    public void PR_FeedFlow(@org.jetbrains.annotations.NotNull()
    android.app.Activity context) {
    }
    
    @java.lang.Override()
    public void PR_ArticleText(@org.jetbrains.annotations.NotNull()
    android.app.Activity context, @org.jetbrains.annotations.NotNull()
    com.newspaperdirect.sdk.analytics.TrackingArticle article) {
    }
    
    @java.lang.Override()
    public void PR_Downloaded(@org.jetbrains.annotations.NotNull()
    android.app.Activity context) {
    }
    
    @java.lang.Override()
    public void PR_Bookmarks(@org.jetbrains.annotations.NotNull()
    android.app.Activity context, @org.jetbrains.annotations.Nullable()
    java.lang.String collection) {
    }
    
    @java.lang.Override()
    public void PR_Search(@org.jetbrains.annotations.NotNull()
    android.app.Activity context, @org.jetbrains.annotations.NotNull()
    java.lang.String term, @org.jetbrains.annotations.NotNull()
    java.lang.String contextName) {
    }
    
    @java.lang.Override()
    public void PR_SearchActivated(@org.jetbrains.annotations.NotNull()
    android.app.Activity context, @org.jetbrains.annotations.NotNull()
    java.lang.String term) {
    }
    
    @java.lang.Override()
    public void PR_SearchDropdownClicked(@org.jetbrains.annotations.NotNull()
    android.app.Activity context, @org.jetbrains.annotations.NotNull()
    java.lang.String type, @org.jetbrains.annotations.NotNull()
    java.lang.String term) {
    }
    
    @java.lang.Override()
    public void PR_Listen(@org.jetbrains.annotations.NotNull()
    android.app.Activity context, @org.jetbrains.annotations.NotNull()
    com.newspaperdirect.sdk.analytics.TrackingIssue trackingIssue) {
    }
    
    @java.lang.Override()
    public void PR_Comments(@org.jetbrains.annotations.NotNull()
    android.app.Activity context, @org.jetbrains.annotations.NotNull()
    com.newspaperdirect.sdk.analytics.TrackingArticle article) {
    }
    
    @java.lang.Override()
    public void PR_SigninForm() {
    }
    
    @java.lang.Override()
    public void PR_SignupForm() {
    }
    
    @java.lang.Override()
    public void PR_Login(@org.jetbrains.annotations.NotNull()
    java.lang.String method, @org.jetbrains.annotations.NotNull()
    java.lang.String service) {
    }
    
    @java.lang.Override()
    public void PR_SignUp(@org.jetbrains.annotations.NotNull()
    java.lang.String method) {
    }
    
    @java.lang.Override()
    public void PR_BillingInfoForm() {
    }
    
    @java.lang.Override()
    public void PR_AddPaymentInfo() {
    }
    
    @java.lang.Override()
    public void PR_IssueDateChanged(int age) {
    }
    
    @java.lang.Override()
    public void PR_FavoriteAdded(@org.jetbrains.annotations.NotNull()
    com.newspaperdirect.sdk.analytics.TrackingIssue trackingIssue) {
    }
    
    @java.lang.Override()
    public void PR_FavoriteRemoved(@org.jetbrains.annotations.NotNull()
    com.newspaperdirect.sdk.analytics.TrackingIssue trackingIssue) {
    }
    
    @java.lang.Override()
    public void PR_AutoDownloadSwitched(@org.jetbrains.annotations.NotNull()
    com.newspaperdirect.sdk.analytics.TrackingIssue trackingIssue, boolean subscribed) {
    }
    
    @java.lang.Override()
    public void PR_Shared(@org.jetbrains.annotations.NotNull()
    java.lang.String content, @org.jetbrains.annotations.NotNull()
    java.lang.String title) {
    }
    
    @java.lang.Override()
    public void PR_Purchase(double value, @org.jetbrains.annotations.NotNull()
    java.lang.String currency) {
    }
    
    @java.lang.Override()
    public void PR_MainMenu(@org.jetbrains.annotations.NotNull()
    java.lang.String selection) {
    }
    
    @java.lang.Override()
    public void PR_AllPaymentOptions() {
    }
    
    @java.lang.Override()
    public void PR_PaymentViewItem(@org.jetbrains.annotations.Nullable()
    com.newspaperdirect.sdk.analytics.TrackingIssue content) {
    }
    
    @java.lang.Override()
    public void PR_PaymentViewItemList(@org.jetbrains.annotations.Nullable()
    com.newspaperdirect.sdk.analytics.TrackingIssue content) {
    }
    
    @java.lang.Override()
    public void PR_PaymentAddToCart(@org.jetbrains.annotations.Nullable()
    com.newspaperdirect.sdk.analytics.TrackingIssue content) {
    }
    
    @java.lang.Override()
    public void PR_Translate(@org.jetbrains.annotations.NotNull()
    java.lang.String from, @org.jetbrains.annotations.NotNull()
    java.lang.String to) {
    }
}