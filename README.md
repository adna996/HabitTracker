# HabitTracker
HabitTracker kotlin app 
# Accomplished aplikacija

Accomplished aplikacije je habit tracker aplikacija koja korisnicima pruÅ¾a moguÄ‡nosti praÄ‡enja
svojih aktivnosti razvrstanih po kategorijama. Kao osnovne karakteristike i prednosti upravo ove habit track
aplikacije izdvojili bismo upravo jednostavanost pri koriÅ¡tenju te lijep, moderan i oku ugodan izgled.
Sama aplikacija kreirana je u Android Studio-u koriÅ¡tenjem Kotlin jezika.
Prilikom izrade aplikacije primjenjivali smo razliÄte tehnike da bismo doÅ¡li do Å¡to bolje funkcionalnosti aplikacije.
U nastavku teksta Ä‡e iste biti opisani, ali uporedo Ä‡emo nastojati objasniti kako arhitekturu aplikacije tako i koncepte
rada sa Room bazom podataka i njenom primjenom kojima smo se vodili prilikom izrade ove aplikacije.

Na samom poÄetku reÄ‡i Ä‡emo neÅ¡to viÅ¡e o naÄinu rada ove aplikacija.  
Dakle, na samom poÄetku se izdvaja Äinjenica da je korisniku
omoguÄ‡eno biljeÅ¾enje dnevnih aktivnosti kao Å¡to su duÅ¾ina sna, broj preÄ‘enih kilometara i sl pri Äemu
je svaka od aktivnosti smjeÅ¡tena u odreÄ‘enu kategoriu koju je sam korisnik ranije definisao.
Prije samog poÄetka praÄ‡enja, korisniku je po default-u ponuÄ‘en odreÄ‘eni broj kategorija (taÄnije 3 kategorije).
Svaka od tih kategorija ima svoje aktivnosti koje mogu biti razliÄitih tipova. Kada govorimo o tipovima aktivnosti svake kategorije
izdvajaju se 3 tipa. Prvi tip su vremenske aktivnosti koje mjere vrijeme izvrÅ¡avanja odreÄ‘ene aktivnosti. Zatim je tu i inkrementalni
tip koji broji odreÄ‘ene poraste ili smanjenja na aktivnosti kao Å¡to je smanjenje broja ispuÅ¡enih cigareta i sl.
TreÄ‡i tip kojeg aktivnost moÅ¾e biti je koliÄinski. Pod koliÄinskim se misli na npr uneseni broj kalorija na dnevnoj osnovi ili sliÄno.
Dakle, korisniku se omoguÄ‡ava da doda novu kategoriju a samo dodavanje se sastoji od davanja imena kategorije
i kratkog opisa iste. Klikom na svaku od kategorija korisniku se prikazuje niz aktivnosti, te mu je
omoguÄ‡eno dodavanje neke nove aktivnosti. Ono Å¡to je bitno istaci jeste da je kako kategorije tako i
aktivnosti moguÄ‡e editovati ili brisati. Naravno korisnici prilikom same rotacije ekrana ne gube
podatke, a to indirektno implicira da je omoguÄ‡en i horizontalni kao i vertikalni layout.

Nakon grubo opisanih principa rada aplikacije, u nastavku dokumentacije nastojat Ä‡emo Å¡to bolje opisati
principe kojim smo se vodili kao i naÄine kreiranja ove aplikacije. Pored tekstualnog opisa,
nastojat Ä‡emo i slikovito doÄarati ono na Äemu smo radili tokom izrade aplikacije.

## **GENERALNO O ARHITEKTURI ANDROID APLIKACIJE**
DugogodiÅ¡nji izbor pri izradi mobilnih aplikacija zasigurno je bila Java, ali pojavom modernih jeziÄkih
prednosti kao Å¡to su lambda funkcije, nullable tipova podataka i tokova podataka tzv stream-ova Kotlin je
polahko ali zasigurno poÄeo da preuzima ulogu vodeÄ‡eg jezika za razvoj i izradu mobilnih aplikacija.
Po definiciji Kotlin je statiÄki tipiziran programski jezik za razvoj multiplatformskih aplikacija.
Kotlin kao jezik je vrlo koncizan, interoperabilan, bezbjedan, sa odliÄnim razvojnim alatima i okruÅ¾enjem.
Iako je Kotlin u upotrebi veÄ‡ neko vrijeme naruÄito u razvoju mobilnih aplikacija, Google i Andorid su
prvi put odluÄili da podrÅ¾e novi programski jezik za neki od svojih proizvoda. Upravu tu se uoÄava prednost
Kotlina, jer kako kaÅ¾u Kotlin je taj koji razvoj Andorid aplikacija Äini lakÅ¡im i interesantnijim.
Andorid platforma je pisana upotrebom Jave ali ni povezivanje sa Kotlinom nije problem s obzirom da su Java i Kotlin
u potpunosti interoperabilni. Å ta to znaÄi? Pa to znaÄi da je omoguÄ‡eno pokretanja java koda u Kotlin
datotekama i obrnuto bez dodtano troÅ¡enja memorije ili vremena. Ovim se omoguÄ‡ilo da aplikacije ne moraju
biti u potpunosti pisane u Kotlinu. Pored toga, kao jedna od bitnijih znaÄajki Kotlina jeste da njegove
moguÄ‡nosti ne zavise primarno od verzije Jave koja se koristi kao niti od verzije Andorida, Å¡to je znaÄajna
razlika u odnosu na Javu.

PreÄ‘imo sada na neke generalne osobine arhitekture aplikacija. Trenutno mjesto najzastupljenijeg arhitektonskog
obrasca u Andoridu zauzima Model-View-Presenter, MVP. Razlog za to leÅ¾i u jednostavnosti, prilagodljivosti kao i brzini.
Kada se uzmu principi troslojne arhitekture i nadograde s navedenim prednsotima android
arhitekture, dobiva se osnovni naÄin rada svake moderne arhitekture koja se koristi za razvoj
android aplikacija. Kreiranjem razliÄitih veza izmeÄ‘u slojeva i dodavanjem dodatnih
odgovornosti, moÅ¾e se izgraditi konkretna arhitektura iz one osnovne.
Jedan primjer takve arhitekture je MVP koja se zasniva se na tri sloja gdje view odgovara
sloju korisniÄkog suÄelja, presenter odgovara sloju poslovne logike, a model odgovara sloju
podataka. Ukoliko odemo korak dalje, Äesto se koristi i Äetvrti sloj interactor koji je posrednik
izmeÄ‘u presenter-a i modela. U tom sluÄaju interactor preuzima odgovornost za poslovnu
logiku, a presenter prima gotove obraÄ‘ene podatke. Svaki sloj ove arhitekture ima jasno definisane
zadatke koje treba da ispunjava.

Model sloj u arhitektonskom sloju jedne aplikacije, reprezentira sloj podataka i kao takav je zaduÅ¾en
za dohvaÄ‡anje i slanje podataka iz bilo kojeg izvora. Izvor moÅ¾e biti web servis, interna baza podataka, lokalni JSON dokument,
bluetooth servisi i sl. Kada sloj primi podatke, oni znaju biti u razliÄitim oblicima ili je moguÄ‡e da dobivamo
suviÅ¡ne podatke koji nam ne trebaju. Da bi smanjili potroÅ¡nju memorije i vrijeme obrade
podataka u sloju poslovne logike, potrebno je dobivene podatke mapirati iz modela podataka u
takozvane domenske modele koji odgovaraju domeni na koju se odnose.

Sloj presenter prima obraÄ‘ene podatke Älanaka od interactor-a i predaje ih u sloj view
gdje Ä‡e se prikazati. BuduÄ‡i da postoji obostrana veza izmeÄ‘u slojeva, tok podataka moÅ¾e iÄ‡i i
u drugom smjeru, odnosno od korisnika prema obradi podataka.

View se odnosi na sam ekran, odnosno na ono Å¡to korisnik moÅ¾e vidjeti na ekranu. On
je odgovoran za korisnikove akcije poput pritiska na gumb, pisanja teksta i sl. Sve podatke koji
se prikazuju na ekranu dobiva od presenter-a ili od korisnika koji ih unese.

Pored MVP-a jedan od poznatijih uzoraka dizajna za arhitekturu je MVC. NajÄeÅ¡Ä‡e se koristi kod izrade
web i desktop aplikacija, dok se za izradu android aplikacija koristi samo u nekim dijelovima
svijeta. MVC arhitektura je vrlo sliÄna prethodno objaÅ¡njenoj MVP arhitekturi, a sadrÅ¾i i dva ista
sloja, model i view dok je razlika u sloju poslovne logike controller. Osim imena, controller se
razlikuje od presenter-a po tome da se u njemu nalazi logika koja odgovara na akcije korisnika.
Dok je u MVP uzorku dizajna svaki view sloj imao svoj presenter sloj, u MVC uzorku dizajna
viÅ¡e view slojeva mogu dijeliti zajedniÄki controller sloj, a on Ä‡e odluÄivati koji view treba
trenutno biti aktivan.U MVP arhitekturi, view i model nisu imali direktnu vezu komunikacije dok je u MVC arhitekturi stvar
drugaÄija. Postoji posebni model prezentacijskoj sloja. View se pretplaÄ‡uje na promjene tog
modela. Kada korisnik napravi akciju na ekranu, akcija se prosljeÄ‘uje s view-a na controller
koji zatim radi promjenu nad modelom podataka. BuduÄ‡i da je view pretplaÄ‡en, on Ä‡e se
indirektnom vezom automatski promijeniti bez da podaci idu nazad preko controller-a. Kod model
sloja ostaje isti kao Å¡to je ranije objaÅ¡njen u MVP arhitekturi. MeÄ‘utim,
njegova svrha viÅ¡e nije da vraÄ‡a podatke u sloj poslovne logike veÄ‡ da ih predaje direktno na
view. Controller ima zadaÄ‡u odgovoriti na akciju korisnika, po potrebi promijeniti view i
zatraÅ¾iti promjenu nad model-om. Za razliku od MVP arhitekture, Controller se ne pretplaÄ‡uje
na promjene u model-u, niti ne predaje nikakve podatke nazad u view veÄ‡ samo manipulira
trenutnim podacima. View se pretplaÄ‡uje na podatke koje Å¾eli prikazati na ekranu. Na neku korisnikovu
akciju, poziva se controller koji je odgovoran za nju. View ne smije znati Å¡to se dogaÄ‘a u
pozadini, veÄ‡ samo zna da oÄekuje nove podatke na koje je pretplaÄ‡en. Kada bismo krenuli u izradu android
aplikacije, morali bismo odabrati neku arhitekturu prema kojoj Ä‡emo raditi. Prilikom odabira,
vaÅ¾no je znati koje su prednosti i koji su nedostaci pojedine arhitekture.

## **DETALJNO O ARHITEKTURI ACCOMPLISHED APLIKACIJE**
U nastavku teksta Ä‡e biti detaljnije opisana sama arhitektura ove, Accomplished, aplikacije. Da bi se uspostavila i sama
funkcionalnost aplikacije potrebno je izmedju ostalog bilo i unutar Gradle skripte unijeti sve potrebne zavisnosti
(a iste su prikazane i na slikama).

<img src="./pictures/gradle-dep.png" height="400">

NaÅ¡a aplikacije je raÄ‘ena uz pomoÄ‡ fragemnata, lokalne baze podataka koriÅ¡tenjem Room-a, kako i druih dobrih praksi za razvoj mobilnih
aplikacija. Na pitanje Å¡ta su to fragmenti u andoridu, odgovor dolazi iz samog naziva. Fragmenti su dijelovi
interfacea same aplikacije ili Äak neka pozadina koja se koristi ili se moÅ¾e koristiti unutar aktivosti.
Interakcija sa fragmentima je ostavrena kroz FragmentManager. Fragment klasa je ta koja se koristi za
predstvaljane nekih kljuÄnih i nitnih operacija koje se vrÅ¡e nad interface-om. Kroz fragmente definiÅ¡emo
njegov lifecycle koji je u zavisnoti od same aktivnosti. Ako je aktivnost uniÅ¡tena i svi fragmenti Ä‡e biti
uniÅ¡teni. U skupini fragmenata koje smo implementirali niti jednom ne bismo umanjili bitnost pa s toga
krenimo od poÄetka.
Unutar layouta Äuvamo upravo sam izgled tzv interface koji se prikazuje samom korisniku aplikacije. Tu podeÅ¡avamo upravo sam
naÄin prikazivanja i upravo je to ranije pomenuti view sloj koji arhitektosnki sloj aplikacije treba da ima. Unutar pomenutih layouta
se definiÅ¡e i izgled kako za horizontalni tako i za vertkialni layout, s obzirom da je iste potrebno podesiti da prilikom rotacije
ekranu korisniku prikazuje jednak sadrÅ¾aj prilagoÄ‘en ekranim u vertikalnim ili horizontalnim poloÅ¾ajima. Ono Å¡to je bitno istaÄ‡i jeste
kako omoguÄ‡iti navigiranje izmeÄ‘u fragmenata/aktivnosti. Ovo se postiÅ¾e pomoÄ‡u navigiranja engl Navigation. Dakle Å¡ta je to navigation?
Sama navigacija se odvija izmeÄ‘u odrediÅ¡ta naÅ¡e aplikacije tj bilo gdje u naÅ¡oj aplikaciji do koje korisnici mogu navigirati.
Ova odrediÅ¡ta su povezana putem akcija. Navigacijski graf engl navigation graph je datoteka s resursima koja sadrÅ¾i sva naÅ¡a odrediÅ¡ta i radnje.
Grafikon predstavlja sve navigacijske "staze" nvaÅ¡e aplikacije. Ali postavlja se pitanje Å¡ta je to potrebno uraditi da bi se obezbjedila navigacija
meÄ‘u fragmentima. Prvo se kreira Resource File, pri Äemu se kao Rescource Type bira navigation. Nakon ovihr adnji Android Studio kreira navigation
resource file. Nakon dodavanja navigacijskog grafa, otvaramo Navigation Editor, gdje moÅ¾emo vizuelno editovati navigacijski graf gdje definiÅ¡emo relacije
tj odnose meÄ‘u fragmentima/aktivnostima. Nakon toga isti je potrebno negdje dodati da bismo dobili ispravan prenos meÄ‘u fragmentima. Nakon Å¡to isti dodamo,
moÅ¾emo dodatno postavljati destinacije. Prvo dodajemo fragmente a onda definiÅ¡emo i odnose meÄ‘u njima. Ovim se omoguÄ‡ava konektovnje i putevi meÄ‘u
fragmentima/aktivnostima. Kada govorimo o konkretnom primjeru na slici ispod je prikazan odnos meÄ‘u naÅ¡im fragmentima i destinacije meÄ‘u njima.

<img src="./pictures/navig.png" height="400">

Unutar svakog fragmenta potrebno je definisati niz aktivnost za isti. Niti jedan od implementiranih fragmenata ne bismo
izdvojali kao viÅ¡e ili manje bitan. Za svaki od fragmenata je pored layouta za isti implementirana i logika koja se veÅ¾e
za radnje koje treba da se dese ukoliko za to ima potrebe. Ono Å¡to je bitno jeste da je ova aplikacija bazirana
na Room bazi podataka pa Ä‡emo s toga za poÄetak objasniti kako opÄ‡enito o Room-u tako i o naÅ¡im primjenama na radu aplikacije.

## **ROOM BAZA PODATAKA**
Za poÄetak se pitamo Å¡ta je to Room? Dakle **Room** je biblioteka koja pruÅ¾a apstrakcijski sloj inzad SQLitea koji omoguÄ‡ava lakÅ¡i
pristup bazi podataka dok koristi u potpunosti snagu i alate SQLitea. Postoje tri osnovne **komponente** Room-a:
1. **Entitet**: Java ili Kotlin klasa koja predstavlja tablicu unutar baze podataka. U naÅ¡em sluÄaju je to *Kotlin* klasa.
2. **DAO**: *Dao* ili Data Access Object je suÄelje koje sadrÅ¾i metode kao Å¡to su getData() ili
	storeData(). Te metode se koriste za pristup bazi podataka, a to suÄelje biti Ä‡e
	implementirano od strane Rooma.
3. **Baza podataka**: Apstraktna klasa koju obuhvaÄ‡a RoomDatabase. Ovdje se definiraju
	entiteti (tablice) i broj verzije baze podataka. SadrÅ¾i drÅ¾aÄ (engl. holder) baze podataka
	i sluÅ¾i kao glavna pristupna toÄka za spajanje.

UoÄavamo pojam SQLite i samim tim se pitamo Å¡ta predstavlja SQLite. SQLite je popularan izbor kao ugraÄ‘eni softver baze podataka za
lokalno pohranjivanje klijenta u aplikativni softver poput web pretraÅ¾ivaÄa. MoguÄ‡e je da je to najraÅ¡ireniji motor baza podataka,
jer ga danas koristi nekoliko raÅ¡irenih preglednika, operativnih sistema i ugraÄ‘enih sistema (poput mobilnih telefona). Iz samog naziva
za oko zapda SQL. Kakva je veza izmeÄ‘u SQL i SQLite-a? SQL je jezik upita dok je s druge strane SQLite je ugraÄ‘eni sistem upravljanja
relacijskim bazama podataka. Dakle, nakon Å¡to smo se malo upoznali sa pojmom SQLite i razlike meÄ‘u SQL-a i SQLite-a nastavimo dalje sa Room-om.


Sam Room ima nekoliko kljuÄnih aspekata koji se razlikuju od tradicionalnih ORM okvira. ORM okviri pruÅ¾aju limitirani set upita,
tabele su deklarirane kao Java objekti, a odnos izmeÄ‘u tabela diktira tip upita koji se mogu obavljati. Kod Rooma su SQL inserti,
aÅ¾uriranja, brisanja i kompleksna spajanja deklarirana kao DAO. Dakle uz sam Room se veÅ¾e i pojam DAO. Da bismo pristupili podacima
aplikacije koristeÄ‡i Room biblioteku, potrebno je da radimo sa data access objects ili skraÄ‡eno DAO. Skup Dao objekata je neÅ¡to kao
glavna komponenta Room-a, pri Äemu DAO ukljuÄuje metode koje pruÅ¾aju abstraktan pristup aplikacijske baze podataka. Da bi se pristupalo
bazi podataka koristimo DAO klase umjesto direktnih upita, moÅ¾emo razdvojiti razliÄite komponente naÅ¡e arhitekture baze podataka. Postoji
nekoliko korisniÄkih upita koji se mogu predstavljati koriÅ¡tenjem DAO klase.

Ono Å¡to mnogi izdvajaju kao prednost Room-a jeste:
1. OmoguÄ‡ava provjere za vrijeme kompiliranja
2. Dobro se poklapa sa LiveData, za nadgledanje uÅ¾ivo koristi LiveDATA
3. Testiranje raznih komponenti u Roomu je vrlo lako
4. Lako se koristi i implementira
5. Smanjuje koliÄinu koda.


Bitno je naglasiti da je sam Room taj koji vodi raÄuna o veÄ‡ini poslova koji se tiÄu postavljanja
i konfigurisanja baze podataka. Naime, veÄ‡ina aplikacija ima podatke koje je potrebno Äuvati i nakon Å¡to
korisnik zatvrori samu apliakciju.

**ENTITET** predstavlja tabelu baze podataka. Room kreira tabelu za svaku klasu koja ima @Entity anotaciju,
a polja unutar te klase odgovaraju kolonama tabele. Same kolone tj Same propertije definiÅ¡emo uz pomoÄ‡ anotacije @ColumnInfo.
Dakle properti je kolona unutar tabele. Ono Å¡to je bitno naglasiti da entiteti ne sadrÅ¾e nikakvu logiku i da oni predstavljaju
neÅ¡to kao male model klase. Kada govorimo o anotaciji @Entity naglaÅ¡avamo da svaka model klasa sa ovom notacijom Ä‡e imati mapiranu
tabelu u bazi podataka. Kao i kod svake tabele moguÄ‡e je dodati primarne kljuÄeve, foreign kljuÄeve i itd. Sve pomenuto se takoÅ¾er definira
koriÅ¡tenjem anotacia. Anotacija @PrimaryKey odnosi se na primarni kljuÄ tabele. Uz autoGenerate koji ako se postavi an true, SQQLite Ä‡e generirati id-eve. Kao
Å¡to smo naveli uz pomoÄ‡ anotacie @ColumnInfo omoguÄ‡avamo specifiranje nekih specifiÄnih informacija o koloni.

**DAO** je odgovoran za definiranje metoda kojima pristupamo podacima. Jednostavno definiÅ¡emo naÅ¡e upite engl queries koriÅ¡tenjem
naravno anotacije @Query unutar DAO klase.

**BAZA PODATAKA** da bi se definisala potrebno je da se definiÅ¡e abstraktna klasa koja extenda RoomDatabase.
Ova klasa ima anotaciju @Database, navodeÄ‡i entitete sadrÅ¾ane u bazi podataka kao i DAO-ove koji im pristupaju.
Klasa sa anotacijom @Database treba da ispunjava odreÄ‘ene uslove:
1. Da bude abstraktna klasa koja nasljeduje RoomDatabase
2. UkljuÄuje listu entitete koje baza podataka sadrÅ¾i sa odgovarajuÄ‡om anotacijom
3. SadrÅ¾i abstraktne metode koje imaju 0 argumenata i vraÄ‡aju klasu koje je sa anotacijom @DAO

Kada govorimo o definisanju upita i funkcija unutar DAO klasa koristimo anotacije @Insert, @Delete, @Update. S druge strane za upite koristimo
anotaciju @Query sa nizom SQLite upita kao parametrom za sve ostale upite.
Same instance baze podataka dobivamo pozivanjem Room.databaseBuilder () ili Room.inMemoryDatabaseBuilder ().

MoguÄ‡e je uraditi razliÄite vrste provjera da vidimo da li naÅ¡a baza podataka kao i DAO rade onako kako se
oÄekuje od njih da rade. Ono Å¡to je potrebno dodati jeste odgovor na pitanje Å¡ta su to anotacije.
Anotaciju su neÅ¡to kao "omotaÄi" za vrijednosti teksutalnih atributa ako atributi imaju karakteristike
kao napomene.

## **IMPLEMENTACIJA ROOM-a NA ACCOMPLISHED APLIKACIJU**
Nakon Å¡to se upoznali sa Room-om sa teoretskog aspekta, preÄ‡i Ä‡emo na detaljniji opis naÅ¡e implementacije.
Potrebno je dodati i odreÄ‘ene Gradle dependencies.
Nakon toga kreiramo i model klase. Kao Å¡to smo veÄ‡ napomenuli Room kreira tabele uz pomoÄ‡ anotacije. Klase Category, Activity i Type predstavljaju
model klase podataka u bazi podataka. Klasa je anotirana sa @Entity i tableName properti koji predstavlja ime tabele. Primarni kljuÄ smo postavili
koriÅ¡tenjem anotacije @PrimaryKey na ispravna polja, konkretno na polje namjenjeno za id kako aktivnosti, tako i kateorije i tipa. TakoÄ‘er smo postavili
ime kolona za polja tabela koriÅ¡tenjem @ColumnInfo(name = â€œime_koloneâ€) anotacijom. Klasa Category nam sluÅ¾i definisanje kategorija unutar same aplikacije.
Ono Å¡to nam je bitno jeste ime i opis same klase. ID broj je ono Å¡to je primarni kljuÄ ove tabele i koristimo atribut autoGenerate = true. Tabelu Activity
korstimo za Äuvanje podataka o samoj aktivnosti. Ono Å¡to je bitno za aktivnost jeste da pored ID te imena i opisa, Äuvamo podatke o kateoriji kojoj akivnost
pripada, te tip aktivnsoti koji moÅ¾e biti inkrementalni, vremenski i koliÄinski. TakoÄ‘er, Äuvamo i atribute value i feeling. value nam je potreban da bismo Äuvali
vijednosti unutar same baze podataka dok feeling koristimo za Äuvanje osjeÄ‡aja prema aktivnosti koje korisnik moÅ¾e unijeti. Na kraju, tabela Type nam je potrebna za
Äuvanje tipa.

<br>
<img src="./pictures/catdb.png" width="400" height="300">
<img src="./pictures/actdb.png" width="400" height="300">
<img src="./pictures/typedb.png" width="400" height="300">
<br>


Nakon Å¡to smo kreirali model klase kreiramo i Data Access Objects (DAOs). Dakle uz pomoÄ‡ DAO-ova smo definirali metode za pristup bazi podataka. Da bismo
kreirali DAO potrebno je da kreiramo interface i koristimo anotaciju @Dao. Unutar DAO klasa smo implementirali sve potrebne metode koje su prikazane na slikama ispod.
Neke od njih su insert podataka, update istih, brisanje kao i brojne druge. Na slici imamo primjer ActivityDao.

<br>
<img src="./pictures/actdao.png" width="600" height="620">
<br>

Na kraju nam preostaje da kreiramo i samu bazu podataka. Da bismo kreirali bazu podataka definiÅ¡emo abstraktnu klasu koja nasljeÄ‘uje
RoomDatabase. Ova klasa koristi anotaciju @Database, listu entiteta sadrÅ¾anih u bazi podataka, i DAO kojim pristupamo podacima.

<br>
<img src="./pictures/appdb.png" width="600" height="620">
<br>
Nakon Å¡to smo detaljno opisali rad sa bazama podataka preÄ‡i Ä‡emo na dio koji govori o upravljanju sa podacima i njihovom implemntacijom na sam projekat.

## **FUNKCIONALNOSTI KLASA**
Dakle naÅ¡a aplikacija se sastoji od ukupno 14 klasa. Svaku od tih klasa Ä‡emo pokuÅ¡ati detaljno pojasnit i obrazloÅ¾iti razlog upotrebe iste.
Da bismo izbjegli nejasnoÄ‡e tokom nastavka teksta upoznat Ä‡emo se sa nekoliko bitnih pojmova.
Fragmenti su uvedeni primarno da bi podrÅ¾ali dinamiÄniji i fleksibilniji UI dizajn. Svaki fragment ima svoj layout i svoje sopstvene lifecycle callback-e i
jedan fragment moÅ¾e se ukljuÄiti u viÅ¡e aktivnosti pa bi se zato trebali dizajnirati za ponovnu upotrebu. Kod Fragment klase je sliÄÂan onom kodu iz Activity klase. U Fragmentima
trebale bi se implementirati sljedeÄ‡e lifecycle metode:
1. onCreate(),
2. oCreateView() i
3. onPause().
RecylerView je widget predstavljen sa Andorid Lollipop verzijom. Predstavlja fleksibilnu i efikasnu verziju ListView-a. Predstavlja neÅ¡to kao kontejner za rendanje veÄ‡ih
skupova podataka. Dakle on je neÅ¡to kao tradicionalni ListView widget, ali sa dosta viÅ¡e fleksibilnosti za prilagoÄ‘avanje i optimizaciju rada sa veÄ‡im datasetovima. NameÄ‡e se pitanje
kada koristiti RecylerView. Onda kada imamo neku veÄ‡u listu sa prilaodovima UI komponentama onda je pogodno koristiti RecylerView.
Da bismo prikazali podatke potrebno je da koristimo RecylerView.Adapter i RecylerView.ViewHolder. RecylerView.ViewHolder predstavlja view-ove naÅ¡e RecylerView-a. Ali Å¡ta je adapter?
RecylerView.Adapter predstavlja podatke koji treba da se prikazuju  sa ViewHolder-om. Unutar samog adaptera mi imamo 3 metode i oni moraju biti override-ane.
1. RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) -> Ova metoda vraÄ‡a nove instance naÅ¡e ViewHolder-a.
2. void onBindViewHolder(ProductViewHolder holder, int position) -> Ovom metodom veÅ¾emo podatke sa view holder-om.
3.  int getItemCount() -> Ova metoda vraÄ‡a veliÄinu liste.
Dakle, objekat Adapter predstavlja neÅ¡to kao most izmeÄ‘u AdapterView-a i osnovnih podataka za taj prikaz.

## MainActivity
Ono Å¡to predstavlja main activity klasa jeste kljuÄ android aplikacije i naÄin na koji su aktivnosti pokrenute i sastavljene. Dakle ona je neÅ¡to kao fundamentalni dio aplikacije,
Å¾ila kucavica. Sama aktivnost nam obezbjeÄ‘uje prozor unutar kojeg se aplikacija prikazuje. TaÄnije prozor unutar koga aplikacija prikazuje svoj UI. Kada govorimo o layout-u unutar
layouta ove klase se nalazi layout StartFragmenta. O StartFragmentu Ä‡emo u nastavku.

## ActivityAdapter
Unutar ove klase smo implementirali adapter koji smo koristili za RecylerView kojim prikazujemo aktivnosti. Dakle pored override-anih metoda, implementirali smo interface metoda koje
smo koristili sa klikanje na pojedinaÄne item-e RecylerView-a. Pored toga za potrebe RecylerView-a smo kreirali i ActivityViewHolder klasu. Unutar iste smo postavili ono Å¡to Å¾elimo
da se prikazje unutar RecylerView-a te funkcije iz interface-a koji su nam potrebne za funkcionalnosti nad itemom.

## CategoryAdapter
Kao Å¡to smo implementirali adapter za Activity analogno smo implementirali i za adapter RecylerView koriÅ¡ten za prikaz kategorija.

## StartFragment
Ovaj fragment predstavlja startni fragment. VraÄ‡a layout koji Ä‡e se prvi prikazivati nako pokretanja aplikacije. Postavljen je listener na dugme TRACK YOUR HABITS. Klikom
na njega koriÅ¡tenjem navigacije prelazimo na ChoiceFragment.

## ChoiceFragment
Unutar ovog fragmenta je nekoliko varijabli koji su nam posluÅ¾ile za ispravnu logiku. Sa varijablom db definiÅ¡emo samu bazu podataka. Varijabla je tipa AppDatabase. Tu je i
varijabla koja nam je posluÅ¾ila za definisanje *RecylerView*-a, kao i floating action dugmeta koji smo koristili za dodavanje nove kategorije.
Sama lista kategorija nam je potrebna za RecylerView i predstavljanje istih. Da bismo dobili kategorije, iste preuzimamo iz same baze podtaka koriÅ¡tenjem metode getAll() koja
nam vraÄ‡a sve katerorije. Metodu smo ranije implementirali unutar *CategoryDao* klase. Na floating action dugme je postavljen clickListener. Klikom na njega pomoÄ‡u navigacije
prelazimo na novi fragment koji nam sluÅ¾i za dodavanje nove kategorije. O ovome fragment Ä‡e biti govora neÅ¡to kasnije. Nakon toga smo postavili sve Å¡to je potrebno bilo za RecylerView.
Pored toga implementirane su i neke override-ane metode. Unutar adaptera potrebnog za postavljanje Adaptera kategorije smo definisali interface sa nekoliko metoda, i o tome Ä‡e
rijeÄi biti neÅ¡to kasnije, koje smo kasnije i implementirali unutar ChoiceFragmenta. Jedna od tih metda je i onItemClick(item: Category, position: Int). Ovo metodom smo omoguÄ‡ili
da otvorimo skup aktivnosti ove kategorije. TakoÄ‘er su tu metode removeItem(item: Category, position: Int) i editItem(item: Category, position; Int). Metodom removeitem smo omoguÄ‡ili
CRUD metodu za birsanje kateorije. Dakle ovom metodom se omoguÄ‡ilo brisanje striktno samo jedne kategorije. Na kraju metodom editItem smo omoguÄ‡ili editovanje kategorije. Ukratko ovim metodama
smo omoguÄ‡ili da klikom na samu kategoriju omoguÄ‡ava se prikaz liste aktivnosti. TakoÄ‘er su omooguÄ‡ene CRUD operacije nad katergoijama metodama removeItem i editItem.

## AddCategoryFragment
Ovakav jedan fragment nam je bio potreban iz razloga implementacije i omoguÄ‡avanju korisniku dodavanje nove kategorije. Nad dugmetom sa id-om submitButton je pozvan clickListener
kojim se omoguÄ‡ava unos nove kategorije u samu bazu podatak. Metodom insertCat smo omoguÄ‡ili unos nove kategorije. Sama metoda je zahtjevala dva parametra od Äega je jedan bio naziv
kategorije a drugi je parametar za opis kategorije. Nakon Å¡to se klikne na dugme, podaci se spaÅ¡avaju u bazu podataka te se vraÄ‡amo na ChoiceFragment.

## CategoryEditFragment
Razlog implementacije ovog fragmenta jeste da se omoguÄ‡i editovanje tj ureÄ‘enje svake kategorije zasebno. Prilikom editovanja kategorije korisnik moÅ¾e da izmjeni ime kategorije ili opis
iste. Na samom poÄetku korisniku se prikazuje ranije uneÅ¡eni tekst. Taj tekst smo naravno preuzeli iz baze podataka uz ViewModel. Ukoliko korisnik Å¾eli da promejni naziv ili opis kategorije
ili pak oboje, nakon unoÅ¡enja Å¾eljenih naziva klikom na edit dugmo podaci se spaÅ¡avaju u bazi podataka te se vraÄ‡amo na ChoiceFragment. Izmjene u bazi podataka smo izvrÅ¡ili primjenom metode
updateCat kojoj smo kao parametar proslijeÄ‘ene varijable cName i cDesc unutar kojih smo saÄuvali izmjene.

## ActivityFragment
Nakon Å¡to smo implementirali funkcionalnosti nad kategorijama ostaje nam da isto uradimo i sa aktivnostima. Unutar ActivityFragmenta ideja je bila da se implementira RecylerView koji nam je potreban
za prikaz svih aktivnosti odreÄ‘ene klase. Za svaku aktivnost se korisniku omoguÄ‡ava da unese novu aktivnost o Äijoj Ä‡e logiÄkoj implementaciji kasnije biti rijeÄi. Zatim, klikom na svaku od aktivnosti
korisniku se u zavisnoti od tipa iste prikazuje interface namjenjen za tu aktivnost. Ukoliko je aktivnost tipa timer tu nam se prikazuje timer sa dugmetom start i stop ali o njegovoj implementaciji
Ä‡e biti govora u nastavku teksta. Ako je aktivnost tipa increment ili quantity prikazuje se potrebni unaprijed definisani interface. TakoÄ‘er korisniku je omoguÄ‡eno da svaku od aktivnosti editujemo.
Kada govorimo o logiÄkoj implementaciji spomenut Ä‡emo nekoliko detalja koje smatramo bitnim. Za potrebe implementacije RecylerView-a potrebna nam je lista unutar koje Ä‡emo Äuvati item za ispis na RecylerView-u.
Sve ove iteme Äuvamo unutar activitiesList koja je tipa Activity, dakle naÅ¡e ranije definasne model klase. Kasnije za implementaciju RecylerView-a koristimo i adapter namjenjen za aktivnosti.
Pored toga klikom na floating action dugme korisniku se omoguÄ‡ava i pruÅ¾a mu se novi interface za dodavanje nove aktivnosti. Uz pomoÄ‡ navigacije prelazimo sa ActivityFragmenta na AddActivityFragment koji Ä‡emo kasnije
detaljnije opisati. TakoÄ‘er kao Å¡to smo rekli potrebno je bilo omoguÄ‡iti da klikom na aktivnost u zavisnosti od tipa aktivnosti potreban interface. NaÄin na koji smo to omoguÄ‡ili jeste ponovo koriÅ¡tenjem
podataka iz baze podataka. Da bismo dobili ispravne podtke o aktivnosti koristimo id. Unaprijed smo definisale tip aktivnosti. Za aktivnost smo iz baze pokupili njen tip. If uslovima smo u zavisnoti od tipa
prikazivali potrebne interface. Unutar navigacije smo definisali argumente kojima se osigurvamo da se za ispravnu aktivnost ispravne kategorije prikaÅ¾emo ono Å¡to nam je potrebno. Ove ID Äuvamo u varijablama actId i catId.
TakoÄ‘er smo i implementirali metodu removeItem kojim smo omoguÄ‡ili da aktivnost moÅ¾emo izbrisati. Isto smo uradili pomoÄ‡u metoda iz DAO koja se zove deleteActivity gdje nam je za ispravno brisanje bio potreban id aktivnosti.
Da bismo omoguÄ‡ili editovanje aktivnosti. Sama funkcionalnost editovanja kategorije Ä‡emo u nastavku detaljnije opisati. Unutar ove metode smo navigacijom koristeÄ‡i id aktivnosti ako argument preÅ¡li za jednog na drui interface.

## AddActivityFragment
Ovim fragmentom smo korisniku pruÅ¾ili da moÅ¾e unijeti novu aktivnost koja Ä‡e biti spremljena u samu bazu podataka. Unutar ove klase smo na poÄetku implementirali korisniÄki interface koji nam je bio potreban za adekvatan prikaza
onoa Å¡to Å¾elimo korisniku prikazati. Nakon Å¡to korisnik unese sve Å¡to je potrebno tj Å¡to se od njega traÅ¾i klikom na dugme SUBMIT se aktivnost adekvatno spaÅ¡ava u bazu podataka. Da bismo obezbjedili adekvatan unos na dugme smo
postavili click listener. Za potrebe spaÅ¡avanja aktivnosti bilo nam je potrebno nekoliko stvari. Jedna od njih je i bilo spaÅ¡avanje atributa aktivnosti tj ukoliko je aktivost tipa increment potrebno je bilo spasiti inkrementalne
vrijednosti koje je korisnik ranije unio. Sa druge strne ako je aktivnost tipa quantity potrebno je bilo spasiti mjerne jedinice za koje se korisnik ranije odluÄio. Isto tako porebno je bilo spasiti i ime aktivnosti. To ime
smo pokupili iz samog korisniÄkog unosa. Na osnovu dobivenih podataka iste je potrebno spasiti u bazi podataka. Unos podataka u bazu podataka smo omoguÄ‡ili metodom insertActivity gdje smo proslijedili ime aktivnosti preuzeno od
korisnikovog unosa, atribut aktivnosti u zavisnosti od tipa iste, tip aktivnosti koji smo spoznali korisnikovim izborom tipa, te na kraju spaÅ¡avamo i id kategorije kojoj je pripadala aktivnost te value koja je prazan string a koji
nam je potreban za kasnije radnje. Naravno pomoÄ‡u navigacije smo prelazili sa jednog interfacea na drugi.
Podatak o tip aktivnosti smo dobili na naÄin da smo prateÄ‡i korisnikov klik isti spaÅ¡avali u varijablu bt te tako kasnije i prikazivali ono Å¡to je korisniku potrebno a kasnije i spaÅ¡avali unutar sam
baze podataka.

## ActivityEditFragment
Implementacijom ovog fragmenta smo korisniku omoguÄ‡ili editovanje same aktivnosti. Dakle klikom na dugme za editovanje aktivnosti smo unutar ActivityFramenta definisali navigacijski prelazak sa jednog na drugi prikaz.Sada je potrebno pojasniti logiÄku implementaciju editovanja aktivnosti. Nakon definisanja bidinga i varijable baze podataka potrebno ostalo je da podatke adekvatno spaÅ¡avamo u bazu podataka. Na samom poÄetku smo
takoÄ‘er implementirali neke funkcionalnosti namjenjene za adekvatan korisniÄki prikaz. Kao i za unos aktivnosti tako i za edit iste potrebno je da znamo informacije koje se edituju da bismo omoguÄili adekvatan update podataka
u bazi podataka. Pored opisanog naÄina za Äuvanja atributa aktivnosti u dijelu gdje smo objanili AddActivityFragment fragmenta, ovdje smo na isti naÄin omoguÄ‡ili da Äuvamo informacija o korisnikovim osjeÄ‡ajima prema samoj aktivnosti.
Definisali smo varijablu updated unutar koje smo spremili podatke za aktivnost dobivene na osnovu uneÅ¡enih podataka. Podatke editujemo u bazu podataka pomoÄu updateAct koju smo ranije definisali unutar DAO klase za aktivnost.
Klikom na dugme edit smo promjene spasili u bazu podataka te navigacijom omoguÄ‡ili povratak na korsniÄki interface namjenjen za prikaz niza aktivnosti unutar RecylerView-a.

## IncrementFragment
Na osnovu tipa aktivnosti koji korisnik unosom nove aktivosti korisniku se klikom na samu aktivnost prikazuje se adekvatan prikaz. Kada govorimo o layoutu korisniku je omoguÄ‡eno pritiskom na dugme poveÄ‡anje ili smanjenje neÄega
Å¡to Å¾elimo mjeriti. Promjene spaÅ¡avamo na dugme SAVE. Kad govorimo o logiÄkoj implementaciji potrebno je prikazivati podatke i Äuvati podatke o ID-u aktivnosti i kateorije. Metodom getAttr implementirane unutar DAO klase ukoliko je
postavljamo vrijednosti. Ovim postavljali tzv increment. Sve koriÅ¡tene metode smo ranije definisali unutar DAO klasa.

## TimeFragment
Ovaj timer nam je potreban za vremenski tip aktivnoti. Princip kojim funkcionisu aktivnosti vremenskog tipa tako Å¡to korisnik klikom na dugme start timer poÄinje da odbrojava dok na dugme stop timer prestaje sa odbrojavanjem.
Layout je implementiran koriÅ¡tenjem chronometera i dugmetom koji nam sluÅ¾e pa pokretanje i stopiranje timera. Potrebno je prikazati vrijednost timera i zato smo koristili medote definisane unutar DAO klase za aktivnosti.

## QuantityFragment
Razlog iz kojeg smo kreirali ovaj frament leÅ¾i u Äinjenici da imamo tri tipa aktivnosti i za svaki od tipova nam je bio potreban fragment pa tako i za quantity tip. Sam layout je kreiran da se korisniku omoguÄ‡ava da unese koloÄinu
onoga Å¡to treba da mjerimo. LogiÄki je fragment implementiran na naÄin da se metodom implemntiranom setValue u DAO klasi uneÅ¡ena vrijednost unosi u bazu podataka i tako radimo update baze podataka.
Navigacijom se vraÄ‡amo na layout unutar kojeg se prikazuju aktivnosti kategorije.


## **UI-KORISNIÄŒKI INTERFACE**
Sam prikaz korisniÄko interface-a je prikazan na slikama ispod.
PoÄetni prikaz aplikacije. Imamo i NavDrawer koji daje link za detaljnije informacije o aplikaciji.

<br>
<div id="container">
<img src="./pictures/start.png" height="400"> .
 <img src="./pictures/sidebar.png" height="400"> .
<img src="./pictures/about.png" height="400">
</div>
<br>

Klikom na dugmiÄ‡ Track your habits otvaraju se kategorije.
Da bi dodali kategoriju, dovoljno je pritisnuti Plus na dnu ekrana. Za editovanje kategorije koristimo dugmiÄ‡ Olovka, a za brisanje Kantu.

<br>

<img src="./pictures/cat.png" height="400"> .
<img src="./pictures/addcat.png" height="400"> .
<img src="./pictures/editcat.png" height="400">

<br>

Klikom na neku od kategorija prikaÅ¾u se aktivnosti definisane u njoj.
Klikom na Plus dodaje se nova aktivnost, na Olovku vodi na do edit-a, dok klikom na Kantu briÅ¡e se aktivnost.

<br>

<img src="./pictures/act.png" height="400"> .
<img src="./pictures/addact.png" height="400"> .
<img src="./pictures/editact.png" height="400">

<br>

Prilikom editovanja ili dodavanja imamo da biramo neke od ponuÄ‘enih karakteristika.

<br>

<img src="./pictures/addincact.png" height="400"> .
<img src="./pictures/quaact.png" height="400">

<br>
U zavisnosti od vrste aktivnosti, tj. da li je ona inkrementalna, kolicinska ili vremenska prikazuje se sljedeÄ‡e:

<br>

<img src="./pictures/incact.png" height="400"> .
<img src="./pictures/quatact.png" height="400"> .
<img src="./pictures/timeract.png" height="400">

<br>

Svaki izbor korisnika direktno se sprema u bazu i vraÄ‡a ga na listu kategorija ili aktivnosti.



