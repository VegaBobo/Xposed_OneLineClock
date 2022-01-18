package vegabobo.xposed_onelineclock

import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

class XposedHook : IXposedHookLoadPackage {

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam?) {

        if (lpparam!!.packageName == "com.android.systemui") {

            val switchController = XposedHelpers.findClass(
                "com.android.keyguard.KeyguardClockSwitchController",
                lpparam.classLoader
            )

            XposedBridge.hookAllMethods(
                switchController,
                "setHasVisibleNotifications",
                object : XC_MethodHook() {
                    override fun beforeHookedMethod(param: MethodHookParam?) {
                        param!!.result = true
                        super.beforeHookedMethod(param)
                    }
                })

        }

    }
}
