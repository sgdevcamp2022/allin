#!/bin/sh

# gcc 설치
sudo apt install -y gcc

# build-essential 설치
sudo apt install -y build-essential

# libpcre3, libpcre3-dev 설치
sudo apt install -y libpcre3 libpcre3-dev

# zlib1g, zlib1g-dev 설치
sudo apt install -y zlib1g zlib1g-dev

# nginx 설치
sudo apt install -y nginx

# nginx rtmp 설치
sudo apt install -y libnginx-mod-rtmp

# ffmpeg 설치
sudo apt install -y ffmpeg


# 포트열기
sudo ufw allow 8088/tcp
sudo ufw allow 1935/tcp

# -------------------------------------------------------

# nginx 설정
# 확인후 rtmp-nginx.conf를 nginx.conf로 변경
sudo mv rtmp-nginx.conf /etc/nginx/nginx.conf

# default 설정
# 확인후 rtmp-default를 deault로 변경
sudo mv rtmp-default /etc/nginx/sites-available/rtmp-default

# rtmp 통계 시각화 파일
sudo mv rtmp-stat.xsl /etc/nginx/rtmp-stat.xsl

# live 위한 폴더 생성
cd /

sudo mkdir video
cd video/

sudo mkdir live
cd live/

sudo mkdir hls

sudo systemctl status nginx.

sudo systemctl stop nginx.service

echo "------------------------------------------------------------------------------------"
echo "cd /etc/nginx로 이동 후 nginx.conf를 확인한 후 mv rtmp-nginx.conf nginx.conf를 통해 변경하세요"
echo ">>>>>>>>>>>>>>>>>>>>>>>>>>>>${MANAGER_SERVER:PORT}<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"
echo ">>>>>>>>>>>>>>>>>>>>>>>>>>>>${MEDIA_SERVER_PORT}<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"
echo "위의 두 변수는 각자 서버에 맞게 반드시 수정하여야 합니다."
echo "cd /sites-availabe로 이동 후 deafult 파일을 확인한 후 mv rtmp-deault default를 통해 변경하세요"
echo "------------------------------------------------------------------------------------"
echo "설정을 변경한 이후 root 권한을 가진 유저로 변경후 systemctl start nginx.service로 nginx를 시작하세요"