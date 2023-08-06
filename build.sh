#!/bin/bash

PROJ_ID=taterlib
PROJ_NAME=TaterLib
VERSION=1.0.0
GROUP_ID=dev/neuralnexus

# --------------------------- Functions --------------------------------

function prepareFiles() {
  # Prepare PLATFORM files
  cp ../$PROJ_NAME-$VERSION-$1.jar ./
  mv ./$PROJ_NAME-$VERSION-$1.jar ./$PROJ_NAME-$VERSION-$1.zip
  unzip ./$PROJ_NAME-$VERSION-$1.zip -d ./$1
  rm -rf ./$PROJ_NAME-$VERSION-$1.zip
}

function build() {
  mkdir -p ./$3

  # Copy common files
  cp -r ./$PROJ_NAME-all/* ./$3/

  # Copy fabric files
  cp -r ./fabric-$1/$GROUP_ID/$PROJ_ID/fabric ./$3/$GROUP_ID/$PROJ_ID
  cp ./fabric-$1/fabric.mod.json ./$3
  cp ./fabric-$1/$PROJ_ID.mixins.json ./$3
  cp -r ./fabric-$1/assets ./$3
  cp ./fabric-$1/fabric-$1-refmap.json ./$3

  # Copy forge files
  cp -r ./forge-$2/$GROUP_ID/$PROJ_ID/forge ./$3/$GROUP_ID/$PROJ_ID
  cp ./forge-$2/pack.mcmeta ./$3
  cp -r ./forge-$2/$PROJ_NAME.png ./$3
  mkdir -p ./$3/META-INF
  cp ./forge-$2/META-INF/mods.toml ./$3/META-INF

  # Zip Jar contents
  cd ./$3
  zip -r ../$3.zip ./*
  cd ../

  # Rename Jar
  mv ./$3.zip ./$3.jar

  # Generate MD5
  md5sum ./$3.jar | cut -d ' ' -f 1 > ./$3.jar.MD5

  # Move Jar
  mv ./$3.jar ../$3.jar
  mv ./$3.jar.MD5 ../$3.jar.MD5
}

function neobuild() {
  mkdir -p ./$4

  # Copy common files
  cp -r ./$PROJ_NAME-all/* ./$4/

  # Copy fabric files
  cp -r ./fabric-$1/$GROUP_ID/$PROJ_ID/fabric ./$4/$GROUP_ID/$PROJ_ID
  cp ./fabric-$1/fabric.mod.json ./$4
  cp ./fabric-$1/$PROJ_ID.mixins.json ./$4
  cp -r ./fabric-$1/assets ./$4
  cp ./fabric-$1/fabric-$1-refmap.json ./$4

  # Copy forge files
  cp -r ./forge-$2/$GROUP_ID/$PROJ_ID/forge ./$4/$GROUP_ID/$PROJ_ID
  cp ./forge-$2/pack.mcmeta ./$4
  cp -r ./forge-$2/$PROJ_NAME.png ./$4
  mkdir -p ./$4/META-INF
  cp ./forge-$2/META-INF/mods.toml ./$4/META-INF

  # Copy neoforge files
  cp -r ./neoforge-$3/$GROUP_ID/$PROJ_ID/neoforge ./$4/$GROUP_ID/$PROJ_ID

  # Zip Jar contents
  cd ./$4
  zip -r ../$4.zip ./*
  cd ../

  # Rename Jar
  mv ./$4.zip ./$4.jar

  # Generate MD5
  md5sum ./$4.jar | cut -d ' ' -f 1 > ./$4.jar.MD5

  # Move Jar
  mv ./$4.jar ../$4.jar
  mv ./$4.jar.MD5 ../$4.jar.MD5
}

# --------------------------- Setup --------------------------------

# Make directories
mkdir -p ./target/temp_build
cd ./target/temp_build

mkdir -p ./$PROJ_NAME-all/$GROUP_ID/$PROJ_ID

# --------------------------- Prepare Common --------------------------------

# Prepare bukkit files
prepareFiles bukkit

# Copy bukkit files
mv ./bukkit/$GROUP_ID/$PROJ_ID/bukkit ./$PROJ_NAME-all/$GROUP_ID/$PROJ_ID
cp ./bukkit/plugin.yml ./$PROJ_NAME-all
rm -rf ./bukkit

# Prepare bungee files
prepareFiles bungee

# Copy bungee files
mv ./bungee/$GROUP_ID/$PROJ_ID/bungee ./$PROJ_NAME-all/$GROUP_ID/$PROJ_ID
cp ./bungee/bungee.yml ./$PROJ_NAME-all
rm -rf ./bungee

# Prepare velocity files
prepareFiles velocity

# Copy velocity files
mv ./velocity/$GROUP_ID/$PROJ_ID/velocity ./$PROJ_NAME-all/$GROUP_ID/$PROJ_ID
cp ./velocity/velocity.yml ./$PROJ_NAME-all/$GROUP_ID/$PROJ_ID
cp ./velocity/velocity-plugin.json ./$PROJ_NAME-all
rm -rf ./velocity

# Prepare common files
prepareFiles common

# Copy common files
mv ./common/$GROUP_ID/$PROJ_ID/common ./$PROJ_NAME-all/$GROUP_ID/$PROJ_ID
mv ./common/$GROUP_ID/$PROJ_ID/lib ./$PROJ_NAME-all/$GROUP_ID/$PROJ_ID
cp ./common/config.yml ./$PROJ_NAME-all
cp ./common/LICENSE ./$PROJ_NAME-all
cp ../../README.md ./$PROJ_NAME-all
rm -rf ./common

# --------------------------- Prepare Forge and Fabric --------------------------------

# Prepare Fabric 1.17 files
FABRIC_VERSION=1.17
prepareFiles fabric-$FABRIC_VERSION

# Prepare Fabric 1.20 files
FABRIC_VERSION=1.20
prepareFiles fabric-$FABRIC_VERSION

# Prepare Forge 1.19 files
FORGE_VERSION=1.19
prepareFiles forge-$FORGE_VERSION

# Prepare Forge 1.20 files
FORGE_VERSION=1.20
prepareFiles forge-$FORGE_VERSION

# Prepare NeoForge 1.20.1 files
NEOFORGE_VERSION=1.20.1
prepareFiles neoforge-$NEOFORGE_VERSION

# --------------------------- Build 1.19 --------------------------------
MC_VERSION=1.19
FABRIC_VERSION=1.17
FORGE_VERSION=1.19
OUT_FILE=$PROJ_NAME-$VERSION-$MC_VERSION

#build $FABRIC_VERSION $FORGE_VERSION $OUT_FILE

# --------------------------- Build 1.20 --------------------------------
MC_VERSION=1.20
FABRIC_VERSION=1.20
FORGE_VERSION=1.20
NEOFORGE_VERSION=1.20.1
OUT_FILE=$PROJ_NAME-$VERSION-$MC_VERSION

neobuild $FABRIC_VERSION $FORGE_VERSION $NEOFORGE_VERSION $OUT_FILE

# --------------------------- Cleanup --------------------------------
cd ../
rm -rf temp_build
