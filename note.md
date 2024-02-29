# 

아키텍쳐 확인
**adb shell getprop ro.product.cpu.abi**

x86

맞는 아키텍쳐 **server** 찾아서 다운 / core 아님

https://github.com/frida/frida/releases

프리다 서버 넣기

**adb push frida-server-16.2.1-android-x86 /data/local/tmp**

권한주기

C:\Users\HP\Downloads>**adb shell**
vbox86p:/ # **su**
vbox86p:/ # **chmod 755 /data/local/tmp/frida-server-16.2.1-android-x86**

실행

vbox86p:/ # **/data/local/tmp/frida-server-16.2.1-android-x86 &**
[1] 3293   //1은 작업번호 3293은 pid

연결 프로세스 확인

C:\Users\HP>**frida-ps -U**
PID  Name

---

2753  Calendar
2822  Clock
2730  Email
2542  Google Play Store

…
3293  frida-server-16.2.1-android-x86

패키지 확인

**adb shell pm list packages**

**adb shell pm list packages | findstr heBb** (윈도우,리눅스는 grep?)

com.ldjSxw.heBbQd

스크립트 주입해서 실행

**frida -U -l "bypass_script.js" -f "com.ldjSxw.heBbQd"**

//

삭제명령어 

rm -r /path/to/your/directory

activity 확인

C:\Users\HP>adb shell dumpsys package com.ldjSxw.heBbQd | findstr -i activity
Activity Resolver Table:
e6f04 com.ldjSxw.heBbQd/.ScanActivity filter 7ca4cf5
d10aced com.ldjSxw.heBbQd/.IntroActivity filter 6f9ea2c
