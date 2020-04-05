

# Portfolio van Jasper
Welkom op het portfolio van Jasper van Es, dit portfolio wordt gemaakt in opdracht van Avans hogeschool voor de proftaak in periode 1.3. Dit portfolio is ontworpen voor de individuele opdracht.

# InhoudsOpgave
1. [Week2](#Week-2)
2. [Week3](#Week-3)
3. [Week4](#Week-4)
4. [Week5](#Week-5)
4. [Week6](#Week-6)
4. [Week7](#Week-7)
4. [Week8](#Week-8)
4. [Week9](#Week-9)
4. [Stelling](#Stelling)


## Week 2
### Bijdrage
In week 2 heb ik gewerkt aan de GUI van de Agenda module. Ik had gewerkt aan het toevoegen van de performances, en het laten zien in de tableview.

<img src="https://github.com/Jaspervanes-github/Proftaak-P1.3-B2/blob/master/portfolio's/resources/Jasper/deel1_addPerformance.png" width="500" height="500">

### Keuzes
Tijdens deze week hebben we aan aantal keuzes moeten maken, een van die keuzes was dat we een soort van opnieuw begonnen waren. Dit hebben we gedaan omdat we onze code niet zo goed snapte, en het makkelijker vonden om sommige dingen opnieuw te maken.

## Week 3
### Bijdrage
Tijdens week 3 heb ik samen met Jin gewerkt aan het deleten en editten van performances. Ook hebben we gewerkt aan het toevoegen, deleten en editten van artiesten. Dit hebben we werkend kunnen krijgen. Aan het eind van de week heb ik nog geprobeerd om te voorkomen dat je dubbele performances kunt toevoegen. Dit is helaas niet gelukt. Verder heb ik deze week het klassendiagram bijgewerkt.

<img src="https://github.com/Jaspervanes-github/Proftaak-P1.3-B2/blob/master/portfolio's/resources/Jasper/Demo_code_Proftaak_p3_B2.jpg" width="1000" height="500">

### Keuzes
In deze week heb we samen met het groepje een beetje na zitten denken welke functies onze Agenda module nog meer moet hebben. Aan de hand daarvan hebben we deze functies gemaakt. We hebben ervoor gekozen om geen Stages toe te kunnen voegen, omdat we later in de map ook niet zo makkelijk een stage toe kunnen voegen.

## Week 4
### Bijdrage
Deze week heb ik samen met Robin de code opgeschoond. Eerst stond bijna alles in één klasse, nadat wij het hadden opgeschoond hadden we de GUI klasse gesplitsts in 3 klassen. Hierdoor was de code een stuk overzichtelijker en duidelijker. Nadat Robin en ik samen de code hadden opgeschoond hebben we een Artist tabje gemaakt. In dit tabje zie je een overzicht van alle artiesten en hun attributen. Ook heb ik Add, Delete en Edit buttons gemaakt. 

<img src="https://github.com/Jaspervanes-github/Proftaak-P1.3-B2/blob/master/portfolio's/resources/Jasper/artist tabje.png" width="1000" height="500">

### Keuzes
Ik heb deze week gekozen om een Artist tabje te maken. De reden waarom ik hiervoor heb gekozen is dat het voor de gebruiker nu een stuk duidelijker is welke artiesten hij/zij heeft toegevoegd.

## Week 5
### Bijdrage
Deze week ben ik begonnen met het werken aan het PvA. Ik ben begonnen aan hoofdstuk 6, en heb later nog wat hoofdstukken nagekeken. Toen ik eenmaal klaar was heb ik gevraagd aan de rest van de groep of ik ergens kon helpen. Ik ben toen Robin gaan helpen met de feedback verwerken van de demo. Wij hebben samen gefixt dat er nu een pop-up verschijnt op het scherm als je een handeling doet.(add, delete, edit) Ook hebben we geregeld dat de add, delete en edit buttons van performance weg zijn als je op het Artist tabje bent, en andersom. 

### Keuzes
Deze week waren er niet veel keuzes om te maken. Alles was vrij duidelijk, iedereen wist wat er moest gebeuren, en hoe wij dat wilde hebben. Ik heb wel de keuze gemaakt om een pop-up te laten verschijnen als je een handeling doet in plaats van dat je niks te zien krijgt.

## Week 6
### Bijdrage
Deze week ben ik begonnen met het maken van de distancemap. Deze ochtend hadden we bij de opstart een link naar een site gekregen, waar psuedocode staat voor het maken van zo'n map. Ik heb dit zo goed mogelijk proberen om te zetten naar java code. Hiermee kwam ik een aardig eind, ik had het gedeeltelijk werken, maar nog niet helemaal. Ook heb ik deze week Dave geholpen met het maken van een extra tabje voor de stages. De bedoeling was dat we net als voor artiesten, en performances ook stages konden toevoegen, deleten en editten. Dit is uiteindelijk gelukt, we hebben ook de file IO hieraan kunnen koppelen.

### Keuzes
We hebben gekozen om een extra tabje toe te voegen voor stages, omdat  dit een vereiste was. We doen verder niks met de stages, omdat we anders ook heel de map aan moeten passen. Ook hebben we gekozen om een distance map te maken, omdat wij hiervoor psuedo code gekregen hadden, en het ons dus makkelijker leek. 

## Week 7
### Bijdrage
Deze week heb ik de distancemap omgegooid naar een directionmap, hier heb ik eigenlijk heel de dag aan gezeten. Dit had ik bijna werkend, op een paar bugs na. Wat later bleek, was dat de isEmpty() niet werkte, daardoor werkte het niet. Dit hebben we op kunnen lossen door in plaats van de isEmpty() aan te roepen, gewoon array.size() <=0 gebruikt.

### Keuzes
Deze week hadden we een aantal keuzes om te maken. Allereerst hebben we overlegd hoe we het aan gaan pakken met het project nu we allemaal thuis zitten door het coronavirus. We hebben besloten om met zn allen in een call in Microsoft Teams te gaan, en zo te communiceren. Ook hebben we besloten om elke maandag en vrijdag een projectdag te houden. Meestal duurt die projectdag van 11:00-17:00. Daarnaast heb ik ook de groep overgehaald om in plaats van een distancemap, een directionmap te gebruiken. Hier heb ik voor gekozen, omdat dit een hoop rekenkracht scheelt tijdens het runnen van het programma. Het voordeel van een directionmap ten opzichte van een distancemap, is dat je bij de directionmap van te voren alles inlaad, en tijdens het runnen alleen maar hoeft te kijken welke kant je op moet. Bij een distancemap moet je namelijk de tegels van de buren vergelijken met de tegel waar je op dit moment opstaat, en kijken welke buur de kleinste distnace tot de target heeft.

## Week 8
### Bijdrage
In deze week heb ik gewerkt aan de pathfinding. Bij de pathfinding was het de bedoeling dat de simulant keek naar de directie vaan de tegel waar hij op dat moment op staat, en die kant op begon te lopen, en ook zijn image aanpaste aan de hand van de richting die hij op liep. Tijdens het testen van de pathfinding kwam ik erachter dat de collisionmap niet helemaal goed gegenereed was, hier zaten dus nog een aantal bugs in. Deze bugs heb ik eruit kunnen krijgen, en toen werkte de pathfinding prima. Ook heb ik deze week de knoppen voor de simulatie gemaakt, en redelijk werkend kunnen krijgen.

### Keuzes
Deze week waren er eigenlijk geen belangrijke keuzes.

## Week 9
### Bijdrage
Deze week heb ik gewerkt aan het samenvoegen van de Agenda module en de Simulatie module, ook heb ik verder gewerkt aan het pathfinding. Het samenvoegen koste niet zo heel veel tijd, omdat we al een tabje hadden voor de simulatie, en we dus alleen maar de inhoud van de tabje hoefde te wijzigen. Wat de pathfinding betreft kooste het wel veel tijd. Ik heb ervoor gezorgd dat de simulanten nu naar de juiste plek gaan op de het juiste tijdstip. De beginnen op de stage waar zij hun eerste performance hebben, en gaan weg als de performance klaar is. De visitors gaan naar een random performance gebaseerd op de populariteit van een artiest. Een artiest met populariteit 10 moet dus theoretisch 10x zoveel mensen hebben als een artiest met populariteit 1. Als er geen performances zijn op dit moment, of de artiest hoeft op dit moment niet op te treden, dan gaan ze drinken halen bij 1 van de 3 cafés. Deze week heb ik ook geimplementeerd dat de artiesten en visitors op een random moment eten of drinken gaan halen, en naar de wc gaan. Dit doen ze als de hunger-, thirst- of peevalue 100 heeft bereikt. Als laatst heb ik er nog voor gezorgd dat de artiesten visitors heen en weer lopen als zij op hun targetpositie zijn, en heb ik ervoor gezorgd dat de artiesten nu een andere image hebben dan de visitors, en dat de animatie van het lopen er nu ook soepel uitziet. 

### Keuzes
Deze week hadden we best wat keuzes te maken. We hebben er bijvoorbeeld voor gekozen om de simulanten gewoon door elkaar heen te kunnen laten lopen. Hier hebben we voor gekozen, omdat als wij het zouden maken dat dit niet kan, dit weer nieuwe problemen zou brengen. Bijvoorbeeld dat simulanten vast komen te zitten in muren, en aangezien de tijd die we nog hadden hebben we dit dus niet gedaan. Ook was er nog iets kleins dat de simulanten op het hek kunnen lopen, dit is een klein foutje bij het genereren van de collisionmap. Maar aangezien de tijd hebben we ook deze bug laten zitten, omdat het verder geen problemen veroorzaakt, maar er alleen soms een beetje gek uitziet.

## Stelling


