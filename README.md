# Obligatorisk oppgave 3 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende student:
* Niklas Havnaas, S356237, S356237@oslomet.no


# Oppgavebeskrivelse

I oppgave 1 så gikk jeg frem ved å sørge for at nullverdier
av verdi ikke blir prossessert, og et error blir kastet
dersom det skulle skje. Initialiserer rot noden og forelder
node samt cmp hjelpevariabel for sammelikning senere.
Så lenge p ikke er null skal q bli forelderen til p og 
hvis cmp er mindre enn 0 skal verdien settes til venstre,
ellers høyre. under det blir det opprettet en ny node med verdi
og en forelder q. hvis q er null (p har ikke forelder) skal p
være rot node, ellers hvis cmp er mindre enn 0 skal p på venstre
side av sin forelder q. Ellers skal p være høyrebarn til q.

I oppgave 2 sørget jeg først for at det ble returnert 0 dersom
verdi = null. Deretter opprettet jeg rot noden p og en teller antall
while løkken går så lenge p ikke er null, og hvis verdi er mindre enn
p.verdi vil p bli satt til venstre for sin forelder, ellers hvis verdi
er større enn p.verdi blir den satt til høyre. hvis verdi = p.verdi
vil den bli satt til høyre og antall vil økes. til slutt returneres 
antall for å vise antall forekomster.

Metoden i oppgave 3 skal ta inn en node p og finne neste node i postOrden.
hvis p.venstre er ulik null vil man følge grenen nedover til p.venstre = null
og dette vil være første neste node i postorden så lenge p.høyre også er null, hvis
det skjer returnerer den denne noden p.

Oppgave 4 hentet jeg kode fra kopendiet for metoden postordenRecursive
og endret litt på navnet til postorden for å få koden til å kalle seg selv.
Deretter lagde jeg postorden ved å opprette en rotnode og kalle den
rekursive metoden for å finne neste i postorden. Etter det opprettet jeg
while løkken som gør igjennom hele treet i postorden til p == null, og dermed
er alle nodene nevnt i postorden rekkefølge.
