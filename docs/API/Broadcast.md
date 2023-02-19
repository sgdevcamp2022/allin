# 방송 매니저 API

## **API DOCS**

### **POSTMAN DOCS**

[https://documenter.getpostman.com/view/17873656/2s935uGg1W](https://documenter.getpostman.com/view/17873656/2s935uGg1W)

### HTTP code 성공 : 200 통일

### **BROADCAST**

### GET Lives

```
curl --location --request GET 'http://3.36.7.55:8080/api/v1/broadcast/lives'
```

```
# Example

{
    "timestamp": "2023-02-12T16:08:39.595+00:00",
    "code": "200400",
    "message": "on live list",
    "data": {
        "liveBroadCasts": [
            {
                "broadCastId": 1,
                "startTime": "2023-02-13T00:59:11.516615"
            },
            {
                "broadCastId": 2,
                "startTime": "2023-02-13T01:00:49.726676"
            }
        ]
    }
}
```

---

### **MANAGER**

### POST LIVE

```
curl --location -g --request POST 'http://3.36.7.55:8080/api/v1/manager/live?name={{key}}&pw={{pw}}'
```

### POST END

```
curl --location -g --request POST 'http://3.36.7.55:8080/api/v1/manager/end?name={{key}}'
```

---

### **PUBLISHER**

### POST SAVE

```
curl --location --request POST 'http://3.36.7.55:8080/api/v1/publisher' \
--data-urlencode 'memberId=1'
```

```
# Example

{
    "timestamp": "2023-02-12T16:11:42.497+00:00",
    "code": "200400",
    "message": "success convert type to Publisher"
}
```

### POST KEY

```
curl --location --request POST 'http://3.36.7.55:8080/api/v1/publisher/key' \
--data-urlencode 'memberId=1'
```

```
# Example

{
    "timestamp": "2023-02-12T16:09:45.428+00:00",
    "code": "200400",
    "message": "success generate key",
    "data": {
        "key": "be320d738302af383e4d375f9b4a121b"
    }
}
```

### PUT UPDATE KEY

```
curl --location --request PUT 'http://3.36.7.55:8080/api/v1/publisher/key' \
--data-urlencode 'memberId=1'
```

```
# Example

{
    "timestamp": "2023-02-12T16:13:01.716+00:00",
    "code": "200400",
    "message": "success update key",
    "data": {
        "key": "8353cb8b935be6e9c6d5810a7ad3b149"
    }
}
```

### POST PASSWORD

```
curl --location --request POST 'http://3.36.7.55:8080/api/v1/publisher/password' \
--data-urlencode 'memberId=1'
```

```
# Example

{
    "timestamp": "2023-02-12T16:09:56.431+00:00",
    "code": "200400",
    "message": "success generate password",
    "data": {
        "password": "2f4acf2f-209a-48a7-9d97-48e8b1ffd64e"
    }
}
```

### DELETE PASSWORD

```
curl --location --request DELETE 'http://3.36.7.55:8080/api/v1/publisher/password' \
--data-urlencode 'memberId=1'
```

```
# Example

{
    "timestamp": "2023-02-12T22:37:39.550+00:00",
    "code": "200400",
    "message": "success reset password, can't use password more time"
}
```

### POST URL

```
curl --location --request POST 'http://3.36.7.55:8080/api/v1/publisher/url' \
--data-urlencode 'memberId=1'
```

```
# Example

{
    "timestamp": "2023-02-12T16:14:56.796+00:00",
    "code": "200400",
    "message": "success generate url",
    "data": {
        "url": "8353cb8b935be6e9c6d5810a7ad3b149?pw="
    }
}
```

---

### **ROOM**

### POST SAVE

```
curl --location --request POST 'http://3.36.7.55:8080/api/v1/room' \
--data-urlencode 'memberId=1' \
--data-urlencode 'title=title' \
--data-urlencode 'description=description' \
--data-urlencode 'startTime=16:00:00' \
--data-urlencode 'endTime=18:00:00'
```

```
# Example

{
    "timestamp": "2023-02-12T16:15:54.576+00:00",
    "code": "200400",
    "message": "success make room"
}
```

### GET INFO

```
curl --location --request GET 'http://3.36.7.55:8080/api/v1/room?roomId=1'
```

```
# Example

{
    "timestamp": "2023-02-12T16:17:09.719+00:00",
    "code": "200400",
    "message": "success browse roomInfo",
    "data": {
        "id": 1,
        "title": "title",
        "description": "description",
        "startTime": "16:00:00",
        "endTime": "18:00:00"
    }
}
```

### PUT EDIT

```
curl --location --request PUT 'http://3.36.7.55:8080/api/v1/room' \
--form 'roomId="1"' \
--form 'title="title"' \
--form 'description="description"' \
--form 'startTime="16:00"' \
--form 'endTime="18:00"'
```

```
# Example

{
    "timestamp": "2023-02-12T16:17:50.964+00:00",
    "code": "200400",
    "message": "success edit roomInfo"
}
```

### GET MEMBER'S ROOM INFO

```
curl --location --request GET 'http://3.36.7.55:8080/api/v1/room/member/1'
```

```
# Example

{
    "timestamp": "2023-02-13T00:00:05.324+00:00",
    "code": "200400",
    "message": "success member's roomInfo",
    "data": {
        "id": 1,
        "title": "title",
        "description": "description",
        "startTime": "16:00:00",
        "endTime": "18:00:00"
    }
}
```

### GET ALL ROOM INFO

```
curl --location --request GET 'http://3.36.7.55:8080/api/v1/room/all'
```

```
# Example

{
    "timestamp": "2023-02-13T00:04:41.856+00:00",
    "code": "200400",
    "message": "success browse all room list",
    "data": {
        "roomList": [
            {
                "id": 1,
                "publisherId": 2
            },
            {
                "id": 2,
                "publisherId": 1
            }
        ]
    }
}
```
