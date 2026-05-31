# 📱 Spendly - Premium Individual & Shared Finance Manager

<div align="center">
  <img width="100%" height="auto" alt="Spendly Banner" src="https://ai.google.dev/static/site-assets/images/share-ais-513315318.png" style="border-radius: 12px;" />
</div>

---

**Spendly** is a state-of-the-art, feature-rich, local-first personal and family financial tracking application built entirely with **Android Studio** using the latest Android development guidelines. 

Designed with a premium dark cosmic aesthetic, **Spendly** offers users an intuitive way to manage shared and individual finances, track budgets, set savings goals, keep tab of upcoming bill deadlines, and view growth analytics—all cached safely and securely on their local device.

---

## 🌟 How Spendly Helps in Day-to-Day Life

Managing finances can be overwhelming. Spendly is designed to solve real-world daily financial problems through an organized and visual experience:

1. **👥 Family & Shared Expense Control**:
   - **The Problem**: Hard to track who spent what in a shared household or among roommates.
   - **Spendly Solution**: Set up custom, color-coded profiles for up to 10 family members or friends. Assign transactions to specific members to see individual contributions or shared expenses at a glance.

2. **📊 Budget Discipline & Overspending Prevention**:
   - **The Problem**: Splurging without realizing you have exceeded your monthly limit on food, shopping, or entertainment.
   - **Spendly Solution**: Define monthly budgets on a per-category level. As you record transactions, Spendly tracks consumption against the limit and warns you when you are running close to or over budget.

3. **🎯 Reaching Financial Milestones (Savings Vault)**:
   - **The Problem**: Finding it hard to save money for specific goals like a new phone, a vacation, or emergency funds.
   - **Spendly Solution**: Create dedicated Savings Vaults with target amounts and dates. Conveniently "deposit" funds into these vaults and monitor progress bar visuals.

4. **📅 Bill Deadline Reminders**:
   - **The Problem**: Forgetting to pay utility, subscription, or rent bills, resulting in late fees.
   - **Spendly Solution**: Add upcoming bills with exact amounts and due dates. View them sorted by urgency, mark them as paid, and simulate alerts to make sure you never miss a deadline.

5. **💱 Dynamic Multi-Currency Adjustments**:
   - **The Problem**: Traveling abroad or tracking international items requires tedious manual conversion.
   - **Spendly Solution**: Easily toggle the active currency between **INR (₹)**, **USD ($)**, and **EUR (€)**. All dashboard totals, lists, and budgets convert dynamically based on exchange rates.

---

## 🛠️ Technology Stack & Architecture

Spendly is developed as a modern native Android application following Google’s official architecture recommendations:

- **IDE**: [Android Studio](https://developer.android.com/studio) (Koala 2024.1.1 or higher recommended)
- **Language**: Kotlin
- **UI Framework**: Jetpack Compose & Material 3 (with custom canvas graphics, ambient gradients, and smooth transition animations)
- **Local Storage**: Room Database (SQLite) for offline-first data persistence
- **Architecture**: MVVM (Model-View-ViewModel) with Clean Repository Pattern and Kotlin StateFlow for reactive, lifecycle-aware UI updates
- **Network & Parsing**: Retrofit, OkHttp, and Moshi (used for currency conversion services)

---

## 🚀 Step-by-Step Installation & Run Guide

Follow these steps to set up the project locally on your machine, build it in Android Studio, and run it on your mobile device.

### 📋 Prerequisites
Before you start, make sure you have the following installed:
* [Android Studio](https://developer.android.com/studio) (Latest version recommended)
* JDK 17 (Usually bundled with Android Studio)
* An Android device running Android 7.0 (API Level 24) or higher with Developer Options enabled OR a configured Android Emulator.

---

### Step 1: Clone or Download the Repository
Get the project files on your local machine:
```bash
git clone https://github.com/AKSHIT1129/SPENDLY-V1.1.git
# Or extract the downloaded source ZIP into a directory of your choice
```

### Step 2: Open the Project in Android Studio
1. Launch **Android Studio**.
2. Click on **File > Open...** (or select **Open** from the welcome screen).
3. Navigate to the directory where you cloned/extracted `SPENDLY-V1.1` and click **OK**.
4. Android Studio will automatically start syncing Gradle and download any missing SDK platforms or dependencies. *This may take a few minutes on the first run.*

### Step 3: Setup Local Environment Config
1. Locate `.env.example` in the root folder of the project.
2. Duplicate or rename `.env.example` to `.env` in the same directory.
3. Open `.env` and set any required API secrets (e.g. `GEMINI_API_KEY=your_key_here`). The app uses a Secrets Gradle Plugin to safely inject keys at build time.

### Step 4: Configure the Build Signing Keystore
To build the application successfully, you have two options for the Android Debug Keystore:

#### Option A: Use the default Android Studio debug keystore (Recommended)
1. Open the app-level `build.gradle.kts` file (located under `/app/build.gradle.kts`).
2. Go to the `buildTypes` block (around line 46-49).
3. Comment out or delete the line:
   ```kotlin
   signingConfig = signingConfigs.getByName("debugConfig")
   ```
4. Perform a **Gradle Sync** (click the elephant icon in the top right of Android Studio). This forces Gradle to use your system's global debug signing configuration.

#### Option B: Decode the custom debug keystore
If you prefer to keep the signing config in `build.gradle.kts`:
1. Open a terminal in the root project folder.
2. Decode the included `debug.keystore.base64` file to reconstruct the `debug.keystore` file:
   * **On Windows (PowerShell):**
     ```powershell
     [System.Convert]::FromBase64String((Get-Content debug.keystore.base64)) | Set-Content debug.keystore -Encoding Byte
     ```
   * **On macOS / Linux:**
     ```bash
     base64 -d debug.keystore.base64 > debug.keystore
     ```

---

### Step 5: Run the App on your Phone

You can choose to run the app on a physical device (highly recommended for performance) or an emulator.

#### Method A: Running on a Physical Android Phone (USB)
1. On your phone, go to **Settings > About Phone** and tap **Build Number** 7 times to unlock **Developer Options**.
2. Go back to the main Settings menu, find **Developer Options**, and enable **USB Debugging**.
3. Connect your phone to your PC via a USB cable. If prompted on your phone, choose "Allow USB Debugging".
4. In Android Studio, look at the device dropdown selector at the top (next to the green Run icon) and select your connected phone.
5. Click the green **Run** button (or press `Shift + F10` / `Control + R`).
6. The app will build, install, and open on your mobile screen automatically.

#### Method B: Running on an Android Emulator (Virtual Device)
1. In Android Studio, open the **Device Manager** (found in the right-side panel or under `Tools > Device Manager`).
2. Click **Create Device**, select a modern phone preset (e.g., Pixel 8), and download a recommended system image (API Level 30+).
3. Complete the wizard and click the **Play** button next to your virtual device to start the emulator.
4. Select the running emulator in the device selector dropdown at the top of Android Studio.
5. Click the green **Run** button to deploy.

---

### 📦 Building a Standalone APK
If you want to compile an installer package (`.apk`) to share with friends or copy to other phones:
1. In Android Studio, go to the top menu and select **Build > Build Bundle(s) / APK(s) > Build APK(s)**.
2. Once the build completes, a popup notification will appear. Click **locate**.
3. Alternatively, navigate to:
   `[Project Folder]/app/build/outputs/apk/debug/app-debug.apk`
4. Copy this file to any Android device, tap on it in the file manager, and install it directly! (Make sure to allow installation from unknown sources in settings).

---

## 🎨 Visual Design Guidelines & Premium Experience
Spendly uses a tailored cosmic theme design. If you are developing features or modifying components, please respect these UI guidelines:
* **Base background**: Deep dark obsidian (`#09080E`) with mixed radial glows in cosmic indigo (`#2C1E3D`) and sunset amber copper (`#C06240`).
* **Accent Highlight Color**: Fintech neon-lime (`#DDF247`) for primary call-to-actions, buttons, progress tracks, and selected tabs.
* **Component Design**: Frosted-glass translucent containers (`#3D1F222C`) with thin 1dp white borders (`#19FFFFFF`) for a modern card finish.
