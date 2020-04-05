

# Portfolio van Dave
Welkom op het portfolio van Dave Visser, dit portfolio wordt gemaakt in opdracht van Avans hogeschool voor de proftaak in periode 1.3. Dit portfolio is ontworpen voor de individuele opdracht.

# Inhoudsopgave
1. [Week 1](#Week1)
2. [Week 2](#Week2)
3. [Week 3](#Week3)
4. [Week 4](#Week4)
5. [Week 5](#Week5)
6. [Week 6](#Week6)
7. [Week 7](#Week7)
8. [Week 8](#Week8)
9. [Week 9](#Week8)
10. [Stelling](#Stelling)
11. [Json Applicaties](#Json)

# Verslag

## Week 1 <a name="Week1"></a>
### Bijdrage
De eerste maandag van het project hebben wij kennis met elkaar kunnen maken. Voorafgaand de eerste dag heb ik alvast contact opgenomen met de rest van de groep door een mail te sturen met daarin de vraag om hun telefoonnummer.
Het begin van deze periode verliep al een stuk gemakkelijker en socialer dan de vorige periode en ik vind dit een positieve vooruitgang.

Op de eerste dag is er een goede rolverdeling gemaakt waaruit is gebleken dat ik de planner van het groepje ben geworden. Dus heb ik een planning gemaakt voor de eerste week. Het projectkantoor is ingericht met Jin. Ook is er een Style Guide gemaakt. Het ontwerp van de agenda heb ik binnen Balsamique gemaakt. Wij hebben ook alvast de keuze gemaakt om te oefenen met GIT en wij hebben een PVA gemaakt op deze dag.

![Foto van de agenda](./Resources/Dave/Agenda.png "Agenda ontwerp")

### Keuzes
Uiteraard had ik aan het begin van de periode kunnen afwachten tot er vanuit hun iets gestuurd werd. Maar ik had zelf geen zin in een groepje wat alleen maar af blijkt te wachten op de reactie van anderen. Om deze reden heb ik de groepsgenoten zelf een mail gestuurd met de vraag om alvast een succesvolle start te maken. Daarmee heb ik ook gelijk de agenda voor de volgende vergadering gemaakt.

Er is bij het ontwerpen van een ontwerp voor de agenda gekozen om voor balsamique te werken. Dit omdat het door johan bij de opstart aangeschreven is om te gebruiken en wij verder nog geen idee hadden van andere programma's die ook wireframes kunnen maken.

### Reflectie
Het allereerste contact leggen met de groep ging soepel. Er werd snel op mijn mail gereageerd en dat vond ik een fijne vooruitgang ten opzichte van de vorige periode.

De eerste dag van de samenwerking met dit groepje ging erg goed. Het beviel mij wel dat er genoeg meningen in het groepje waren maar er was ook een ontspannen sfeer.


## Week 2 <a name="Week2"></a>
### Bijdrage
De eerste week dat er gewerkt wordt aan de code heb ik een barebone programma gemaakt waar de rest van het groepje aan kan werken. De klassen hebben allen ook direct hun eigen attributen gekregen.

### Keuzes
Het ontwerpen van de standaardcode die iedereen kan gebruiken is een keuze die ik heb gemaakt om zo iedereen via GIT in ieder geval al een goede start te geven qua code. Het zorgt er namelijk voor dat er een eenheid is en niet dat verschillende mensen met andere code werken.
Het maken van deze code heeft samen met Jasper niet zo heel lang geduurd dus bij het pushen naar de git-repo kon de groep al binnen een uur aan de slag.
Bij het ontwerpen van het klassendiagram is er gekeken naar de eigenschappen waar de agenda module aan moest voldoen. Onder anderen heeft de klasse stage dus enkel een capaciteit (hoeveelheid mensen) en een naam. De klasse artiest heeft een genre die uit de enum Genre gehaald wordt. Er is gekozen voor een enum omdat dit gemakkelijk te implementeren is.

### Reflectie
Over het algemeen ben ik erg tevreden over het geleverde werk van week 2, de groep vond de opstelling van de code goed en overzichtelijk en daarom wordt dit tot nu toe nog steeds gebruikt.
Allereerst vond ik het klassendiagram niet heel erg goed en na het seniorgesprek wat deze ochtend gevoerd is hebben wij deze dus nog aangepast om meer overeen te komen met de huidige code.

## Week 3 <a name="Week3"></a>
### Bijdrage
In deze week heb ik samen met robin gekeken naar het toevoegen van een performance waar artiesten/stages en tijden aan toegevoegd moesten worden, in eerste instantie werkte alleen de tijden maar aan het einde van deze week is het gelukt om de performances toe te voegen. Ook heb ik mij gericht op het maken van de plattegrond met Tiled, hieronder zul je meer zien van deze creatie. Hierna hebben Robin en ik een poging gedaan om een foto 
### Keuzes
![Foto van de plattegrond](./Resources/Dave/Map.jpg "Terrain")

Bij het ontwerpen van het terrein heb ik rekening gehouden met de verschillende stages en de ruimtes die hiervoor benodigd waren. We hebben gekozen voor 4 stages bijbehorende bij de 4 grote velden. De simulatie heeft ook een plaats waar de simulanten kunnen uitrusten/eten of toiletteren.
Of de simulanten volledig gebruik gaan maken van de geplaatste faciliteiten is nog niet 100% zeker, maar we hebben er dus wel voor gekozen om het mogelijk te maken om deze te plaatsen.

Rechtsboven zullen de simulanten kunnen eten. Dit is een klein veld waar zij kunnen genieten. Er is gekozen voor 1 toilet omdat het een fictioneel toilet is en dus ondergronds meer ruimte zal bevatten indien benodigd.


### Reflectie

Het verwerken van de Tiled map was leuk werk maar de terrain editor werkte niet zo gemakkelijk met onze tileset zoals het bij Johan ging bij de opstart. Om die reden heeft het ontwerpen hiervan iets meer tijd gekost dan de bedoeling was. Ik en Bart hebben samen een poging gewaagd aan het programma en wij zijn er beiden niet uitgekomen met dit probleem. Mocht dit in een verder stadium van het project een probleem opleveren bij het onderdeel pathfinding dan zullen wij overwegen om een nieuwe map te maken. 


## Week 4 <a name="Week4"></a>
### Bijdrage
In deze week heb ik samen met Jin gekeken naar het uitlezen van de Json map, het inladen van dit bestand ging nog niet zo gemakkelijk als er in eerste instantie gedacht was. Dit zal later nog aan onze senior Etienne gevraagd moeten worden.
Verder heb ik deze week een begin gemaakt om het project onder te verdelen in verschillende modules, aldus de agenda en simulatie module. Dit heb ik gezien bij een les van Etienne en wilde dit zelf ook toepassen echter heeft dit nogal wat problemen veroorzaakt waardoor het oplossen hiervan niet zo gemakkelijk bleek te zijn.
De aanpassingen hiervan werken uiteindelijk wel maar zijn verder nog niet geimplementeerd.
Ook heb ik deze week nog gewerkt aan het ontwerp van de simulatie, dit heb ik binnen Balsamique gemaakt.

### Keuzes
Binnen deze week hebben wij een keuze moeten maken voor het gebruiken van 2 verschillende applicaties of 1 all-in-one. Allereerst hebben wij gekozen om gebruik te maken van 2 verschillende applicaties. De bedoeling was om met twee modulen te werken zoals Etienne bij 2D graphics heeft laten zien dat dat mogelijk is. Later op de projectdag hebben wij gezien dat het best wel lastig gaat en dat de uitleg op internet niet voldoende was om dit succesvol te implementeren. Dan komen wij uit op het punt van het simulatie ontwerp. Hierin hebben wij vervolgens al besloten het programma toch uit te gaan voeren binnen 1 applicatie doormiddel van verschillende tabjes. Dit is gekozen omdat de uitwerking hiervan toch gemakkelijker is voor nu.

![foto van simulatie ontwerp](./Resources/Dave/Simulatie-Ontwerp.png "Simulatie ontwerp")

### Reflectie

Het werken met Jin ging goed we hebben samen gekeken naar het probleem ondanks dat het uiteindelijk nog niet 100% gelukt was. We hebben elkaar goed ondersteund door online te zoeken naar eventuele voorbeelden. Bij het maken van de modules heeft Jin mij ook proberen te helpen door voorbeelden op te zoeken. Dit waardeerde ik erg en ik vond het dus ook fijn om deze week met Jin samen te werken.

Het maken van het ontwerp is vrij gemakkelijk gegaan, balsamiq is een gemakkelijk programma, het enige probleem nu is dat de licentie verlopen is en ik dus niks meer aan kan passen aan het bestand.

## Week 5 <a name="Week5"></a>
### Bijdrage
Binnen deze week heb ik samen met Bart en Robin gekeken naar het maken van de PVA dit moest namelijk voor P&OC ingeleverd worden. Dus moesten er nog een stel hoofdstukken aangepast worden. Dit is gedaan door grotendeels de gehele groep.

### Keuzes

Omdat er deze dag een deadline stond voor het PVA hebben wij besloten dat dit de prioriteit heeft ten opzichten van het project. Bij het maken van het PVA is er een onderverdeling geweest voor het maken van de opdrachten. Gezamenlijk wordt hoofdstuk 1 gedaan. Ik heb aan hoofdstuk 4 gewerkt en iedereen had de taak om de andere hoofdstukken na te lezen op fouten.

### Reflectie
Het maken van dit PVA ging best goed. Al hadden wij hier natuurlijk eerder aan kunnen beginnen om dit probleem helemaal te voorkomen. Ondanks dat we er te laat aan waren begonnen hebben wij dit wel tijdig in kunnen leveren.

## Week 6 <a name="Week6"></a>
### Bijdrage
Samen met Jasper hebben wij deze week ons gericht op het maken van een distance map.
Ook heb ik mij deze week vooral gericht op een extra feature die in de lijst van requirements stond, namelijk het toe kunnen voegen van stages. Omdat dit eerder nog niet geimplementeerd was heb ik ervoor gekozen om dit te gaan verwerken deze week. Aan het einde van de dag is dit met succes afgerond. Dit moet enkel nog geimplementeerd worden door de code beheerder.

### Keuzes

Bij het maken van deze feature heb ik de keuze gehad om deze te verwerken in de bestaande klasse of deze allemaal te verwerken in een nieuwe klasse. Ik heb er voor gekozen gezien de tijd en prioriteit om dit in dezelfde klasse te behouden. De code is gebaseerd op de code van de artist en performances. Dit zorgt ervoor dat de layout in het uiteindelijke programma hetzelfde zal zijn.

```
    public void writeFileStage(String filename, ObservableList<Stage> list) throws FileNotFoundException {

        ArrayList<Stage> stages;

        if(list instanceof ArrayList<?>){
            stages = (ArrayList<Stage>) list;
        } else {
            stages = new ArrayList<>(list);
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("src/File_IO/" + filename);
            ObjectOutputStream objectInputStream = new ObjectOutputStream(fileOutputStream);
            objectInputStream.writeObject(stages);
            fileOutputStream.close();
        } catch(IOException e){
            System.out.println("Exception in writeFilePerformances()");
            e.printStackTrace();
        }

    }

    public ArrayList<Stage> readFileStage(String filename) throws FileNotFoundException, EOFException {

        ArrayList stages = new ArrayList();

        try {
            FileInputStream fileInputStream = new FileInputStream("src/File_IO/" + filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            stages = (ArrayList<Performance>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (EOFException e){
            System.out.println("File is empty");
        } catch (FileNotFoundException e){
            System.out.println("File not found, generating a new one...");
        } catch (ClassNotFoundException e){
            System.out.println("Class not found in readFileArtist()");
            e.printStackTrace();
        } catch (IOException e){
            System.out.println("Exception in readFileArtist()");
            e.printStackTrace();
        }

        return stages;
    }
```

### Reflectie

Het toevoegen van dit onderdeel ging vrij gemakkelijk omdat de code gebaseerd is op al bestaande code. Enkel liep ik nog wel tegen een paar kleine probleempjes aan zoals het schrijven naar een bestand. Wij kregen namelijk iedere keer een probleem terug met een String Property. Hier heb ik uiteindelijk samen met Jin en Jasper naar moeten kijken, een int waarde werd niet ondersteund terwijl in de parameter een int meegegeven moest worden. Dit was een lastig stuk maar is uiteindelijk op te lossen door de onderstaande manier.

## Week 7 <a name="Week7"></a>
### Bijdrage
Wegens de corona maatregelen hebben wij onze werkwijze aan moeten passen en zitten wij vanaf nu thuis te werken. Binnen deze periode zullen wij via Teams elkaar ondersteunen en verder aan de opdracht werken. Omdat alles even wennen was en sommige Teams en skype for business niet werkend kregen hebben ik met diegene waaronder bart mee gekeken naar zijn software problemen.

Binnen deze week hebben wij vooral gewerkt aan het genereren van de distance map. Hiervoor hebben wij in de middag hulp gekregen van Etienne.

### Keuzes

Deze week hebben wij besloten om gebruik te maken van een direction map. Dit laat dus zien waar de simulant heen moet om bij zijn doel te komen doormiddel van up,down,left,right.

### Reflectie

Het thuis werken is wel een aanpassing, toch vind ik het wel fijn om zo te kunnen werken omdat je alles in de buurt hebt. en ik hoef geen 3 uur te reizen om thuis te zijn.
Wat betreft het samenwerken gaat dat wel enigzins lastiger omdat dit niet zo heel erg gemakkelijk te doen is. Meekijken is namelijk afhankelijk van de snelheid van sommige mensen hun internet.

## Week 8 <a name="Week8"></a>
### Bijdrage
In week 7 hebben wij feedback gehad op ons eerste ingeleverde PVA. Dit was echter niet zoals we hadden gehoopt en daardoor moest er dus nog het een en ander aangepast worden aan het product. 
Onder deze aanpassing viel dat wij teveel vanuit Avans Hogeschool hadden geschreven in plaats van Serious Sims.
Deze aanpassing hebben ik en Bart samen gemaakt voor het grootste gedeelte terwijl Robin en Jasper verder gingen programmeren voor het project.
Jasper en Robin hebben er deze week voor gezorgd dat de personen naar hun doelen liepen.

### Keuzes

Wij hebben de keuze gemaakt om met Bart het PVA aan te gaan passen omdat het in ons geval makkelijker was gezien Jasper het meeste ervaring heeft met programmeren en het snelste de oplossingen ziet. Bij het maken van het PVA hebben bart en ik een taakverdeling gemaakt op basis van de beoordeling per hoofdstuk. Wij hebben dus beiden hoofdstukken gemaakt waar veel punten te behalen vielen. Na afloop hebben wij dit allemaal ook nog nagelezen. Het verbeterde document moest Woensdag 25 Maart ingeleverd zijn en wij hadden dus nog genoeg tijd om dit te laten doorlezen door de andere groepsgenoten.

Door de manier van werken via teams hebben wij wel Jasper kunnen helpen op sommige momenten door het geluk van scherm delen.

### Reflectie
Ook in deze week kregen wij weer te maken met problemen van het op afstand werken. Het meekijken bij Jasper ging niet zo gemakkelijk omdat er soms internet vertragingen waren.
Ondanks deze vertragingen hebben wij deze week op het gebied van de simulatie en het PVA goede vorderingen kunnen maken. Het PVA is op de 25ste ingeleverd na goedkeuring van de groep.

## Week 9 <a name="Week9"></a>
### Bijdrage
In deze week hebben wij vooral gezorgd dat alles van het programma werkte, hierbij hebben wij verschillende keuzes moeten maken die hieronder beschreven zullen staan. Verder heb ik deze week samen met Jasper nagedacht over het probleem waardoor artiesten soms vast bleven staan bij een stage als hun performance is afgelopen. Dit kwam doordat de artiesten dachten dat zij nog een optreden hadden dus konden zij niet bewegen. Verder heb ik deze week een opzet gemaakt voor de presentatie van aankomende maandag.

### Keuzes
Deze week zijn er niet heel veel keuzes gemaakt. Los van dat we gezamenlijk besloten hebben dat er bepaalde dingen prioriteit hebben in het project en dus andere aspecten iets minder belangrijk zijn. Hieronder valt dat de getekende map waarover de simulanten kunnen lopen iets te breed was waardoor ze over hekjes heen lopen. Dit hebben wij maar zo gelaten omdat dit geen probleem veroorzaakte in het product verder. Daarnaast is het product niet extreem goed geoptimaliseerd maar het kan werken tot een bepaalde hoeveelheid aan simulanten (20). Dit hebben wij gezien de tijd ook maar gelaten omdat het product verder wel voldoet aan de eisen.

### Reflectie
Het thuis werken ging al een stuk gemakkelijker, dit kwam grotendeels doordat ik aan de presentatie gewerkt had en dus niet echt hulp nodig had van anderen. De presentatie is wel bekeken door de groepsgenoten en zij zullen hun stukje bewerken waar nodig.

## Stelling Onderzoek <a name="Stelling"></a>
### Introductie

Binnen dit rapportage is het de bedoeling dat de student zijn/haar mening geeft op een gegeven stelling. De mening zal onderbouwt worden middels het gebruik van artikelen uit de praktijk. Aan het einde zal de student terugkijken op de eerst gegeven mening. 


### Reflectie en Onderzoek

In periode 1.2 van de opleiding Technische Informatica zijn wij geintroduceerd tot de libraries van JavaFX en de functionaliteiten die daarbij behoren. De vele documentatie die hierbij beschikbaar is heeft in het leerproces zeer behulpzaam gebleken.
Binnen dit verslag wordt er onderzoek gedaan naar de volgende stelling: "In het bedrijfsleven wordt gebruik gemaakt van JavaFX".
Alvorens het onderzoeken van deze stelling is mijn eerste mening dat JavaFX niet gebruikt zal worden bij projecten van grote bedrijven. Grotere bedrijven maken vaker hun eigen frameworks die precies voldoen aan de eisen van het bedrijf. Kleinere bedrijven zullen eerder gebruik maken van de kant en klare libraries om hun producten te maken. Omdat dit tijd en dus geld bespaart.

Om te onderzoeken of bedrijven gebruik maken van JavaFX ben ik als volgt aan de slag gegaan, allereerst heb ik op internet gezocht naar "real-world" applicaties die gemaakt zijn op basis van JavaFX componenten. In dit onderzoek wordt gekeken naar verschillende applicaties uit [deze link](https://jaxenter.com/20-javafx-real-world-applications-123653.html). Het bedrijf Ai-solutions heeft voor ruimte reis organisaties het programma "Deep Space Trajectory Explorer" ontwikkelt, hierin hebben zij eigen JavaFX tools gebouwdt die met hoge snelheid en grote datasets kan werken. In de video fragmenten op de site is een menu te zien die doormiddel van het JavaFX framework in elkaar is gezet. hieruit blijkt dat grote organisaties weldegelijk gebruik maken van JavaFX.

![foto van simulatie ontwerp](./Resources/Dave/DSTE-PIC-ONE.png "Deep Space Trajectory Explorer")

Ook in de gezondheidssector wordt er gebruik gemaakt van het framework, een voorbeeld hiervan is het programma FORUM, dit programma wordt gebruikt door het bedrijf Carl Zeiss Meditec AG. Het is ontwikkelt door Saxonia Systems AG en het is een mix tussen Swing en JavaFX die gemigreerd wordt.
Voor dit pgramma was het belangrijk dat beiden technologien samen gebruikt konden worden zolang de migratie nog niet afgerond is. Dit programma is ontworpen om een duidelijk beeld te geven van de ooggezondheid van patienten. Binnen het programma kunnen verschillende documenten van patienten geopend worden via een mix van Swing en JavaFX aspecten. 

![foto van simulatie ontwerp](./Resources/Dave/FORUM-PIC-ONE.jpg "FORUM")

Bij het onderzoeken van deze stelling is naar boven gekomen dat meer bedrijven gebruik maken van JavaFX dan aan het begin van het onderzoek mijn mening was.


### Conclusie

Binnen het bedrijfsleven wordt er wel gebruik gemaakt van het JavaFX framework, ookal lijkt het alsof dit meer gebruikt wordt bij non-profit organisaties.
Mijn eerste mening over het gebruik van het JavaFX framework klopte niet, bedrijven maken wel gebruik van het framework maar voegen zonodig wel eigen functies toe, dit hebben we gezien bij het Deep Space Trajectory Explorer programma. Zoals te zien was bij het programma FORUM wordt er tegenwoordig voor belangrijke programma's die op oudere frameworks draaien gezorgd dat die over gezet kunnen worden naar JavaFX. Omdat het belangrijk is dat bij essentiele programma's voor in de zorg en bij de overheid er geen dataverlies geleden mag worden moet er gecontrolleerd aanpassingen gemaakt worden aan de software. JavaFX en Swing maken deze overstap gelukkig mogelijk. 
Concluderend was mijn eerste mening over het gebruik van JavaFX dus niet correct. Binnen het bedrijfsleven wordt wel degelijk gebruik gemaakt van JavaFX. Het onderzoeken van deze stelling bleek nog vrij lastig gezien er weinig informatie over bekend is over programma's die gebruik maken van JavaFX.

### Bronvermelding

https://jaxenter.com/20-javafx-real-world-applications-123653.html

https://ai-solutions.com/deep-space-trajectory-explorer/

https://www.zeiss.com/meditec/us/products/ophthalmology-optometry/ophthalmology-pacs-forum/enterprise.html


## Json Applicaties <a name="Json"></a>
### Introductie

Binnen dit hoofdstuk wordt er door de student onderzocht welke applicaties/games gebruik maken van het Json formaat.

Json is een lichtgewicht data uitwisselings formaat, die het voor mensen gemakkelijk te lezen en schrijven moet maken.

Het is gebaseerd op een onderdeel van het JavaScript Programming Language Standard ECMA-262 3rd Edition - December 1999.

Het is gebouwd op twee structuren.
- Een collectie met namen/waarden paren.
- Een georderde lijst met waarden.

```
{
    "string": "Hi",
    "number": 1,
    "object": {"stuff": "foobar", "digit": 13},
    "array": ["spam", "foo"]
}
```

Een voorbeeld van Json code.


### Applicaties

De onderstaande applicaties /games maken gebruik van het Json formaat. Hieronder zal ook vermeld worden waarvoor de applicaties dit formaat gebruiken.

- Minecraft 
    - Binnen Minecraft wordt er gebruik gemaakt om verschillende typen data op te slaan. Denk hierbij aan de text in boek objecten voor in het spel. De data voor het gebruikersprofiel.
    Informatie over gedownloadde versies van het spel.
    - Voor meer informatie: https://minecraft.gamepedia.com/JSON#cite_note-1

- Adobe Creative Cloud apps
    - Ook binnen de apps van Adobe wordt er gebruik gemaakt van Json, hier zijn verschillende gebruiksredenen voor, een van die redenen is het bijhouden van de versie's van de applicaties. Ook worden er bepaalde strings opgeslagen voor referentie bij het installeren en gebruiken van de apps. Een deel van die strings Json file staat hieronder vermeld.
    ```
    {
    "cloudsync.cloudstoragetile.progress.label": "CLOUD STORAGE",
    "cloudsync.cloudstoragetile.upgrade": "Upgrade",
    "cloudsync.cloudstoragetile.storage": "{usedStorage} of {availableStorage} used",
    "cloudsync.footertile.syncfolder": "Open sync folder",
    "cloudsync.footertile.creativecloud": "Creative Cloud web",
    "cloudsync.cloudstoragetile.storage.above.ninety": "Your storage is almost full. Upgrade to keep things running smoothly.",
    "cloudsync.cloudstoragetile.storage.hundred": "Your cloud storage is full. Upgrade now to continue adding new work.",
    "cloudsync.asyncupdatefailtile.title": "Creative Cloud update failed",
    "cloudsync.asyncupdatefailtile.description": "Sorry, we couldn't update the app.",
    "cloudsync.asyncupdatefailtile.errorlink": "Error Code {code}",
    "cloudsync.updatetile.title": "Updating {name}"}
    ```
- Firefox
    - Firefox zelf maakt geen gebruik van Json, bij het installeren van plugins kan het zijn dat deze een Json manifest file aan maakt Eventuele andere Json files kunnen worden aangemaakt. Onderstaand is een Json manifest file van de Media Plugin "ClearKey Gecko Media Plugin",
    ```
    {
    "name": "clearkey",
    "description": "ClearKey Gecko Media Plugin",
    "version": "1",
    "x-cdm-module-versions": "4",
    "x-cdm-interface-versions": "10",
    "x-cdm-host-versions": "10",
    "x-cdm-codecs": "avc1"}
    ```
- Office
    - De apps van het office pakket, maken ook allemaal gebruik van verschillende bestanden van het Json formaat. Hier staan onder andere de PowerPointCapabilities.json files, deze zijn er voor alle office apps en houden onder andere de versies bij van onderliggende modules.


### Conclusie

Er zijn heel veel applicaties en games die gebruik maken van het Json formaat, allen maken zij gebruik van het formaat voor verschillende doeleinden. In dit lijstje zijn maar 4 applicaties opgenomen maar er zijn er dus ontelbaar veel die op een simpele of complexe manier gebruik maken van het formaat.

### Bronvermelding

- https://minecraft.gamepedia.com/JSON#cite_note-1
- https://www.json.org/json-nl.html
- Windows Menu Search, (Gezocht in de directories van applicaties naar Json files  )









