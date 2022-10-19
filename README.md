# BusTrackerService
installation

docker run --name BusTrackerService -d -p 8080:8080 elledian/bus_tracker_service:latest

or 

run run.sh script on the cloud server

![Mobil Uygulama]([))
 <a href ="[https://www.java.com](https://www.google.com](https://github.com/omerfarukkykc/React-Bus-Tracker" target ="_blank"> Mobil Uygulama</a>

![image](https://user-images.githubusercontent.com/54690370/196819339-f826753e-ebbc-4082-9dbc-2ae47033521b.png)
Nesneye dayalı ve ilişkili veri tabanı kullanılarak anahtar değer eşleştirmeleri ile daha az yer kaplayan ve daha anlaşılır bir veri tabanı oluşturulmuştur. İlişkili veri tabanı temelde, tekrar tekrar kullanılacak verilerin bir kere hafızada oluşturulduktan sonra aynı verinin kullanılacağı diğer kısımlarda hafızada halihazırla oluşturulmuş veriye referans verme işini yapar. Görselde oluşturulan veri tabanı yapısı projede oluşturulacak veri tabanının taslağı olmakla birlikte geliştirilmesi hedeflenmektedir. Projede yer alması beklenen tablolar ve ilişki tanımları;

3.3.1.	Users Tablosu
Kullanıcı verilerinin tutulduğu tablolardır. Bu tabloda kullanıcının ad, soyad, eposta, ve konum bilgileri tutulacaktır. Bu temel verilere ek kullancıyı refere edecek id değeri tanımlanmıştır. Kullanıcıların konum bilgisi yalnızca izin veren kullanıcılardan ve uygulamanın açık olduğu durumlarda alınacaktır.

3.3.2.	Rol Tablosu
Kullanıcıların farklı yetki düzeyleri bulunmaktadır buna örnek olarak, otobüs şoförü otobüs bilgilerini düzenleyebilmelidir veya Firmanın seferlerini ayarlayan personeller sefer ekleyebilmelidir. Bu sebeple her kullanıcının farklı yetki düzeyi olmalıdır. Rol tablosu yetkilerin tutulduğu ve hangi yetkinin uygulamalardaki hangi özelliklere erişim sağlayabileceğinin bilgisini tutan tablodur.

3.3.3.	UserRols Tablosu
Bir kullanıcınım birden fazla rolü olabilirken, bir rol birden fazla kullanıcıya atanabilir. Bu sebeple çoka çok bir ilişki oluşturmamız gerekir. Bunun için UserRols ara tablosu oluşturulup çoka çok ilişki sağlanmış olur. Bu tabloda UserID ve RolID verileri tutulur.

3.3.4.	Alarms Tablosu
Kullanıcıların kurdukları alarm bilgileri yer almaktadır. UserID, BusID ve StationID referanslarsları ve alarmın etkinleşeceği zaman bilgisi burada tutulmaktadır.

3.3.5.	ApiAccess Tablosu
Mobil uygulama web uygulaması ve otobüslerin veri alıp gönderebilmesi ve sistemin istismar edilmemesi için servera erişirken anahtar değerler kullanılacak. Bu anahtar değerler Yetkili kullanıcılara tanımlanacak. Bu sebeple UserID, ApiAccessID ve ApiKey Bilgileri burada yer alacak. 

3.3.6.	ApiRol Tablosu
Kullanıcı erişiminde yer alan kısıtlama sistemi Api erişiminde de yer alacağından her anahtarın belirli yetkileri olacaktır. Bu sayede örneğin otobüste yer alan akıllı sistem sadece konum bilgilerini sisteme iletme yetkisine sahip olacaktır. Diğer tablolara erişim sağlayamayacaktır.

3.3.7.	ApiRols Tablosu
UserRols tablosunda olduğu gibi Api rollleri ile Anahtarlarının çoka çok ilişki ile birleştirilmesini sağlayan ara tablodur.



3.3.8.	Stations Tablosu
Durak bilgilerinin tutuldğu tablodur. Durakların konum isim bilgileri bu tabloda yer alır. Aynı zamanda durağın kapsama alanı olan dairenin çap bilgisi burada yer alır. Dairenin çapı durağın belirtildiği noktanın etrafındaki alanı hesaplamak için kullanılan bir tam sayı değeridir. Durak kapsamı, ilgili alandaki yolcu sayısını bulmak için gereklidir. 

3.3.9.	Travels Tablosu
Sefer ücreti, sefer adı, sefer kalkış ve varış saat bilgileri bu tabloda yer almaktır.

3.3.10.	TravelRotation Tablosu
Seferlerin uğradığı durak bilgileri bu tabloda tutulur. Örneğin bu tabloda bir sefer ile birden fazla durağın eşleştirildği birden fazla kayıt vardır. Bu kayıtlar bir aradayken anlamlı bir sefer bilgisi elde edilmektedir. Durakların sıralaması tabloda yer alan değerlerden birisidir. Bu ara tablo yöntemi ile verimli bir şekilde güzergâh bilgileri tutulmaktadır.

3.3.11.	Buses Tablosu
Otobüsün Plaka, Model, Marka, Kapasite ve konum bilgisi bu tabloda yer almaktadır. 

3.3.12.	BusLocations Tablosu
Otobüslerin konum bilgileri bu tabloda tutulur sürekli olarak güncelleme işlemi yapılacağından Otobüsler tablosundan ayrılmıştır. Otobüsler tablosu ile birebir ilişki kurulmuştur.

olarak sıralandırılabilir.
