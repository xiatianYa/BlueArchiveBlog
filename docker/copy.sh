#!/bin/sh

# 复制项目的文件到对应docker路径，便于一键生成镜像。
usage() {
	echo "Usage: sh copy.sh"
	exit 1
}


# copy sql
echo "begin copy sql "
cp ../sql/ry_20230706.sql ./mysql/db
cp ../sql/ry_config_20220929.sql ./mysql/db

# copy html
echo "begin copy html "
cp -r ../blue-ui/dist/** ./nginx/html/dist


# copy jar
echo "begin copy blue-gateway "
cp ../blue-gateway/target/blue-gateway.jar ./ruoyi/gateway/jar

echo "begin copy blue-auth "
cp ../blue-auth/target/blue-auth.jar ./ruoyi/auth/jar

echo "begin copy blue-visual "
cp ../blue-visual/blue-monitor/target/blue-visual-monitor.jar  ./ruoyi/visual/monitor/jar

echo "begin copy blue-modules-system "
cp ../blue-modules/blue-system/target/blue-modules-system.jar ./ruoyi/modules/system/jar

echo "begin copy blue-modules-file "
cp ../blue-modules/blue-file/target/blue-modules-file.jar ./ruoyi/modules/file/jar

echo "begin copy blue-modules-job "
cp ../blue-modules/blue-job/target/blue-modules-job.jar ./ruoyi/modules/job/jar

echo "begin copy blue-modules-gen "
cp ../blue-modules/blue-gen/target/blue-modules-gen.jar ./ruoyi/modules/gen/jar

