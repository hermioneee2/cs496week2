# 한다리건너

![overthebridgewithname.png](%E1%84%92%E1%85%A1%E1%86%AB%E1%84%83%E1%85%A1%E1%84%85%E1%85%B5%E1%84%80%E1%85%A5%E1%86%AB%E1%84%82%E1%85%A5%200696e2ede63e4360bfd632f5798d96ce/overthebridgewithname.png)

> “음.. 주변에 괜찮은 사람 소개시켜 줄 수 있어?”
”삼성전자에 가고 싶은데, 혹시 아는 사람 있어요?”
> 

“한다리건너”는 한다리건너 사람들과 쉽고 빠르게 연결할 수 있는 앱입니다. 친구를 직접 거치지 않고도 친구의 지인과 연락할 수 있고, 부끄럽다면 앱 안에서 사람을 찾은 후 친구에게 부탁할 수도 있죠! 내 지인을 직접 이어주기 부담스럽거나, 지인의 지인과 연락하고자 할 때 사용할 수 있습니다.

## 기능 개요

![KakaoTalk_20220712_202718316_03.jpg](%E1%84%92%E1%85%A1%E1%86%AB%E1%84%83%E1%85%A1%E1%84%85%E1%85%B5%E1%84%80%E1%85%A5%E1%86%AB%E1%84%82%E1%85%A5%200696e2ede63e4360bfd632f5798d96ce/KakaoTalk_20220712_202718316_03.jpg)

![KakaoTalk_20220712_202718316.jpg](%E1%84%92%E1%85%A1%E1%86%AB%E1%84%83%E1%85%A1%E1%84%85%E1%85%B5%E1%84%80%E1%85%A5%E1%86%AB%E1%84%82%E1%85%A5%200696e2ede63e4360bfd632f5798d96ce/KakaoTalk_20220712_202718316.jpg)

![KakaoTalk_20220712_204623772.jpg](%E1%84%92%E1%85%A1%E1%86%AB%E1%84%83%E1%85%A1%E1%84%85%E1%85%B5%E1%84%80%E1%85%A5%E1%86%AB%E1%84%82%E1%85%A5%200696e2ede63e4360bfd632f5798d96ce/KakaoTalk_20220712_204623772.jpg)

- **0. LOG IN**
카카오톡으로 로그인하기를 통해 ‘한다리건너’에 가입할 수 있습니다. 회원 등록을 위해서는 연락처와 문자에 관한 권한에 동의해야 하며, 본인의 연락처나 관심사 기본 정보를 함께 적습니다.
- **1. HOME TAB**
나의 관심분야와 같은 사람들을 모아볼 수 있어요. 위에 검색을 통해 원하는 분야의 사람을 찾을 수 있죠! 예를 들면 삼성전자를 검색하면 삼성전자에 다니는 사람들을 찾을 수 있어요. 사람을 클릭하면 그사람의 정보를 볼 수 있고, 직접 연락하거나 친구를 통해 연락을 부탁할 수 있어요.
- **2. PROFILE TAB**
내 정보가 저장된 공간이에요. 상단의 Edit Profile 버튼을 통해 내 정보를 수정하고 관심사를 추가할 수 있어요.
- **3. NOTIFICATION TAB**
나의 친구가 나의 또 다른 친구에게 관심을 보이는데 직접 연락하기 부끄럽다고 해요. 친구가 보낸 알림을 모아보고 친구 대신 문자로 연락을 해줄 수 있어요. 다리를 이어줍시다!

## **설치 링크**

APK 다운로드 링크: 

### 개발 환경

- OS: Android (26 or Later)
- Language: Kotlin / JavaScript
- DB: Neo4j
- Sever: node.js
- IDE: Android Studio / Visual Studio Code
- Target Device: Galaxy Note 8

## 1. LOG IN

![KakaoTalk_20220712_202718316_06.jpg](%E1%84%92%E1%85%A1%E1%86%AB%E1%84%83%E1%85%A1%E1%84%85%E1%85%B5%E1%84%80%E1%85%A5%E1%86%AB%E1%84%82%E1%85%A5%200696e2ede63e4360bfd632f5798d96ce/KakaoTalk_20220712_202718316_06.jpg)

![KakaoTalk_20220712_202718316_05.jpg](%E1%84%92%E1%85%A1%E1%86%AB%E1%84%83%E1%85%A1%E1%84%85%E1%85%B5%E1%84%80%E1%85%A5%E1%86%AB%E1%84%82%E1%85%A5%200696e2ede63e4360bfd632f5798d96ce/KakaoTalk_20220712_202718316_05.jpg)

### 주요 기능

- 카카오톡으로 로그인하기를 통해 ‘한다리건너’에 가입할 수 있습니다.
- 회원 등록을 위해서는 연락처와 문자에 관한 권한에 동의해야 하며, 본인의 연락처나 관심사 기본 정보를 함께 적습니다. 관심사는 태그의 단위로 기록되고, 카테고리는 직장/전문분야/취미/연애상태 등로 나뉩니다.
- 관심사의 경우 자동완성을 지원합니다.

### 기술 설명

- 카카오톡 SDK를 활용하여 로그인을 구현하였습니다.
- LoginActivity에서 카카오톡으로 로그인을 하면, 사용자토큰을 서버로 보내 DB에 존재하는 토큰인지 확인합니다. 이미 존재할 경우 처음 정보 및 관심사를 등록하는 절차인 InitProfileActivity와 InitFriendsActivity를 건너뜁니다.
- InitProfileActivity와 InitFriendsActivity에서는 사용자의 정보를 받고, 두번째 스텝까지 등록을 마치면 서버에 사용자토큰과 함께 DB에 정보를 보냅니다.

## 1. HOME TAB

![KakaoTalk_20220712_202718316_03.jpg](%E1%84%92%E1%85%A1%E1%86%AB%E1%84%83%E1%85%A1%E1%84%85%E1%85%B5%E1%84%80%E1%85%A5%E1%86%AB%E1%84%82%E1%85%A5%200696e2ede63e4360bfd632f5798d96ce/KakaoTalk_20220712_202718316_03%201.jpg)

![KakaoTalk_20220712_202718316_04.jpg](%E1%84%92%E1%85%A1%E1%86%AB%E1%84%83%E1%85%A1%E1%84%85%E1%85%B5%E1%84%80%E1%85%A5%E1%86%AB%E1%84%82%E1%85%A5%200696e2ede63e4360bfd632f5798d96ce/KakaoTalk_20220712_202718316_04.jpg)

### 주요 기능

- 홈 화면에서는 내가 관심사로 등록한 태그를 가지고 있는 한다리건너 친구들을 모아볼 수 있습니다. 프로필사진을 클릭하면 그 친구들의 정보를 보거나 연락할 수 있습니다.
- 상단바의 검색을 통해 원하는 친구를 찾을 수 있습니다. 친구의 이름을 직접 검색해볼 수도 있고, “삼성전자”, “농구” 등의 원하는 태그를 등록할 수도 있습니다.
- 연락하고 싶은 친구가 있다면 그 친구의 정보 탭 아래 “직접 연락하기” 또는 “한다리 건너기”를 할 수 있습니다.
    - 직접 연락을 하는 경우 그 친구에게 ‘한다리건너’를 통해 알게 되었다는 문자를 보낼 수 있습니다.
    - “한다리 건너기”를 선택한 경우 그 친구와 나의 연결다리인 사람에게 연락이 갑니다. 사용자는 그 연결다리가 누구인지 직접 확인할 수 없지만 연결다리인 사람에게는 알림이 뜹니다.
- 한다리건너 친구들을 모아볼 수 있지만 연결다리가 누구인지 공개되지는 않습니다.

### 기술 설명

- HomeFragment에서 두가지 RecyclerView중 하나를 선택해서 보여줍니다. 첫번째로 태그 별로 사람을 모아서 보여주기 위해, ItemTagModal 오브젝트를 기반으로 ItemTagAdapter를 통해 RecyclerView에 알맞은 정보를 전달합니다. 태그 리스트는 나의 태그를 리턴합니다.
- 두번째는 검색 결과에 따라 사람과 그 사람의 관심사가 담긴 태그를 보여주기 위해 ItemModal 오브젝트를 기반으로 ItemAdapter를 통해 알맞은 정보를 전달합니다.
- ItemModal은 한 개인에 관한 정보(이름, 프로필사진, 번호, 이메일, 직장 태그 등)를 모두 저장하는 단위입니다.
- 두가지 RecyclerView 모두 한 RecyclerView안에 다른 Recyclerview를 넣어 구현하였습니다.
- DB로부터 정보를 요청하는 것은 맨 상단인 HomeFragment에서 한번만 이루어지며, 나머지 하위 Fragment는 위의 정보를 받아 화면에 띄워줍니다.
- ‘직접 연락하기’ 버튼을 누르면 Intent를 통해 문자를 적을 수 있는 공간으로 이동합니다.
- ‘한다리 건너기’ 버튼을 누르면 서버에 연결을 요청한 사람과 연결을 당하는 한다리 건너의 사람 간에 임시 관계를 생성합니다. 버튼을 누른 사용자에게는 토스트 메시지만 간략히 띄웁니다.

## 2. PROFILE TAB

![KakaoTalk_20220712_204902032.jpg](%E1%84%92%E1%85%A1%E1%86%AB%E1%84%83%E1%85%A1%E1%84%85%E1%85%B5%E1%84%80%E1%85%A5%E1%86%AB%E1%84%82%E1%85%A5%200696e2ede63e4360bfd632f5798d96ce/KakaoTalk_20220712_204902032.jpg)

![KakaoTalk_20220712_202718316.jpg](%E1%84%92%E1%85%A1%E1%86%AB%E1%84%83%E1%85%A1%E1%84%85%E1%85%B5%E1%84%80%E1%85%A5%E1%86%AB%E1%84%82%E1%85%A5%200696e2ede63e4360bfd632f5798d96ce/KakaoTalk_20220712_202718316%201.jpg)

### 주요 기능

- 나의 프로필을 확인할 수 있는 공간입니다.
- 상단의 Edit Profile 버튼을 통해 내 정보를 수정하고 관심사를 추가할 수 있습니다.

### 기술 설명

- ProfileFragment와 ProfileEditFragment 두가지를 만들어 버튼을 누르면 두가지 Fragment간을 오고갈 수 있도록 구현하였습니다.
- ProfileFragment는 activity_item과, ProfileEditFragment는 activity_item_editable과 연결지어져 있고, 새로운 탭추가를 위해 창을 추가로 띄워주고 TextView가 EditText로 대체된다는 차이 외에 거의 모든 부분이 같습니다.

## 3. NOTIFICATION TAB

![KakaoTalk_20220712_202718316_01.jpg](%E1%84%92%E1%85%A1%E1%86%AB%E1%84%83%E1%85%A1%E1%84%85%E1%85%B5%E1%84%80%E1%85%A5%E1%86%AB%E1%84%82%E1%85%A5%200696e2ede63e4360bfd632f5798d96ce/KakaoTalk_20220712_202718316_01.jpg)

![KakaoTalk_20220712_204623772.jpg](%E1%84%92%E1%85%A1%E1%86%AB%E1%84%83%E1%85%A1%E1%84%85%E1%85%B5%E1%84%80%E1%85%A5%E1%86%AB%E1%84%82%E1%85%A5%200696e2ede63e4360bfd632f5798d96ce/KakaoTalk_20220712_204623772%201.jpg)

### 주요 기능

- 나에게 오는 알림을 모아보는 공간입니다. 내가 연결다리인 경우에 알림을 볼 수 있습니다.
- 여기서 알림이란, 나의 친구 A가 나의 또다른 친구 B에게 연락하고자 하는데, 나를 통해 연락하고 싶다는 알림입니다. 친구가 1번의 “한다리 건너기”를 눌렀을때 나에게 알림이 오는 방식입니다.
- 오른쪽 버튼의 이어주기를 누르면 B에게 문자를 보낼 수 있습니다. 문자의 내용은 A가 B를 알고 싶어하며, A의 연락처를 함께 알려주는 내용입니다.

### 기술 설명

- 사용자가 앱을 처음 켤 때, 내가 아는 지인 중 서로 임시관계를 맺고 있는 사람이 있는지 확인합니다. 있는 경우 데이터를 로드하여 NotificationFragment에서 RecylerView를 이용하여 두 사람이 연결되고자 한다는 정보를 보여줍니다.

## 4. SERVER

### DB: Neo4j

- 선택 이유: 사람과 사람간의 관계를 쉽게 쿼리하고, 앱의 확장 가능성을 위해 그래프 기반의 DB인 Neo4j를 사용하였습니다.

### Server: NodeJS, Express

- NodeJS와 Express를 이용해 RESTful API를 구현했습니다. GET, POST 등의 HTTP 프로토콜을 사용하여 JSON 형태의 어플리케이션 정보를 주고받을 수 있습니다.

- **Authors**

이혜림(카이스트 18): [https://github.com/hermioneee2](https://github.com/hermioneee2)

윤태영(한양대 20): [https://github.com/taeyoung-1](https://github.com/taeyoung-1)

## COPYRIGHT

- 아이콘 이미지
    - [https://www.flaticon.com/free-icon/global-network_4207230?term=network&page=1&position=2&page=1&position=2&related_id=4207230&origin=search](https://www.flaticon.com/free-icon/global-network_4207230?term=network&page=1&position=2&page=1&position=2&related_id=4207230&origin=search)
    - [https://thenounproject.com/icon/quiet-4181231/](https://thenounproject.com/icon/quiet-4181231/)
    - [https://thenounproject.com/icon/shy-4181215/](https://thenounproject.com/icon/shy-4181215/)
    - [https://thenounproject.com/icon/excited-4181224/](https://thenounproject.com/icon/excited-4181224/)
