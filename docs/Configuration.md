# Common configuration settings

Most of the settings resides in `pressreader_sdk_config.xml`

### UI Customizations

| **Name** | **Description** | **Is Required** |
|--|--|--|
| `color_brand` | Main accent color (tint colour of buttons, highlights, some other action controls.) | yes |
| `color_brand_dark` | Secondary accent color |  |
| `color_background_dark` | Catalog background |  |
| `brand_gradient_start_color` | Start gradient colour of brand modal screens (Branded Edition Splash Screen, Onboarding, Subscription, etc.). |  |
| `brand_gradient_end_color` | End gradient colour of brand modal screens |  |
| `is_popup_article_view_allowed` | Show article in popup on tablet when it fits the screen |  |

### Graphic resources

| **Name** | **Description** | **Is Required** |
|--|--|--|
| `@drawable/logo.png` | Splash screen logo image | yes |
| `@drawable/splash_image_background.png` | Splash screen background image | yes |
| `@drawable/logo_image_placeholder.png` | Loading image placeholder | yes |
| `@drawable/oem_logo_toolbar.png` | Toolbar logo image | yes |

**Please note:** If these graphic resources aren’t overriden, then SDK will use default one which aren’t related to any certain publisher. In order to override mentioned graphic resources an integrator of SDK should create corresponsing png files and add them at least to drawable folder. Also, we recommend to create and put resources for all main Android densities such as mdpi, hdpi etc.

### Service configuration params

| **Name** | **Description** |
|--|--|
| `service_name` | Pressreader system registered service name. This has to be provided by Pressreader company. |
| `privacy_policy_url` | Link to online privacy policy |
| `terms_of_use_url` | Link to online terms of use |
| `registration_url` | Link to online account creation page if account creation has to be managed by third-party provider. (optional) |
| `password_recovery_url` | Link to online password recovery page, if account is managed by third-party provider |
| `twitter_url` | Link to partner Twitter account |
| `youtube_url` | Link to partner’s Youtube channel |
| `facebook_url` | Link to partner’s Facebook page |
| `instagram_url` | Link to partner’s Instagram page |
| `pref_feedback_email` | Support contact e-mail |
| `pref_feedback_phone_number` | Support contact phone |
| `pref_feedback_show_support` | Show contact support section in settings |
| `enroll_in_onboarding` | Enabled onboarding during initial first launch |

### Navigation bar customization

The parameters allow to show/hide buttons on the navigation bar.

| **Name** | **Description** |
|--|--|
| `sdk_exit_button` | Show the button which closes PressReader Activity |
| `sdk_exit_button_text` | Exit button caption |
| `sdk_homefeed_button` | Show the home feed button |
| `sdk_catalog_button` | Show the publications catalog button |
| `sdk_mylibrary_button` | Show my library button |
| `sdk_accounts_button` | Show accounts button for the PressReader flow or accounts UI on More screen in the Branded Edition flow |
| `sdk_settings_button` | Show settings button for the PressReader flow or settings UI on More screen in the Branded Edition flow |
| `sdk_bookmarks_button` | Show bookmarks button for the PressReader flow |

### Feature flags

| **Name** | **Description** | **Default** |
|--|--|--|
| `bookmarks_enabled` | Enable bookmarking articles |
| `bookmarks_enabled` | Enable bookmarking articles |
| `comments_enabled` | Enables commenting feature | true |
| `new_payment_flow_supported` | Enables new payment flow. Checkout “New Payment Flow” section to customize string resources according your requirements. |
| `enable_social_signin` | Enabled sign-in with social providers.  Need to provide `facebook_app_id`, `facebook_app_secret`  |
| `hide_register` | Disable creating account in application |
| `hide_sharing` | Disable sharing content in application |
| `is_free_app` | Removes creating/sign-in options in app. All content should be free. |
| `favourites_enabled` | Enabled/Disable favorited newpaper titles |
| `radio_support` | Enable/Disable listen feature |
| `translate_enabled` | Enable/Disable translation feature |
| `single_title_mode` | Enable single newspaper mode for Home screen |
| `newsfeed_api` | Home RSS article provider (should be provider by Pressreader): 0 - no RSS article; 2 - RSS source based on favorited newspaper title. Each newspaper title should have configured its own RSS channel; 3 - All Rss sources are imported into single RSS channel |
| `vote_enabled` | Enable/disable voting functionality for an article |
| `hyphenation_disabled` | Enable/disable hyphenation for article details |
| `reader_external_links_enable` | Enable/disable external links navigation and rendering for Article Details and Replica | true |