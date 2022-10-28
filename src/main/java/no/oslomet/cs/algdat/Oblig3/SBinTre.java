package no.oslomet.cs.algdat.Oblig3;


import java.util.*;

public class SBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public SBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    public boolean leggInn(T verdi) {  //Hentet fra kompendiet
        //throw new UnsupportedOperationException("Ikke kodet ennå!");
        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        Node<T> p = rot, q = null;               // p starter i roten
        int cmp = 0;                             // hjelpevariabel

        while (p != null)       // fortsetter til p er ute av treet
        {
            q = p;                                 // q er forelder til p
            cmp = comp.compare(verdi,p.verdi);     // bruker komparatoren
            p = cmp < 0 ? p.venstre : p.høyre;     // flytter p
        }

        // p er nå null, dvs. ute av treet, q er den siste vi passerte

        p = new Node<>(verdi,q);                   // oppretter en ny node

        if (q == null) rot = p;                  // p blir rotnode
        else if (cmp < 0) q.venstre = p;         // venstre barn til q
        else q.høyre = p;                        // høyre barn til q

        antall++;                                // én verdi mer i treet
        return true;                             // vellykket innlegging
    }

    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int fjernAlle(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int antall(T verdi) {
        //throw new UnsupportedOperationException("Ikke kodet ennå!");

        if(verdi == null) return 0;
        Node<T> p = rot;
        int antall = 0;

        while(p != null){
            if(comp.compare(verdi, p.verdi) < 0){
                p = p.venstre;                            //Hvis verdi er mindre enn p blir verdien satt til venstre
            }else if(comp.compare(verdi, p.verdi) == 0){  //hvis p = verdien har den en forekomst
                antall++;                                 //og antall skal økes med en
                p = p.høyre;                              //Setter p til høyre siden
            }else{
                p = p.høyre;                              //Hvis p er større enn verdi,setter verdi til høyre for p
            }
        }
        return antall;          //Returnerer antall forekomster av verdi
    }

    public void nullstill() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    private static <T> Node<T> førstePostorden(Node<T> p) {
        //throw new UnsupportedOperationException("Ikke kodet ennå!");
        while(true){
            if(p.venstre != null) p = p.venstre;
            else if(p.høyre != null) p = p.høyre;
            else return p;
        }
    }

    private static <T> Node<T> nestePostorden(Node<T> p) {
        //throw new UnsupportedOperationException("Ikke kodet ennå!");
        /*
        Postorden:

Hvis p ikke har en forelder (p er rotnoden), så er p den siste i postorden.
Hvis p er høyre barn til sin forelder f, er forelderen f den neste.
Hvis p er venstre barn til sin forelder f, gjelder:
        Hvis p er enebarn (f.høyre er null), er forelderen f den neste.
        Hvis p ikke er enebarn (dvs. f.høyre er ikke null),
        så er den neste den noden som kommer først i postorden i subtreet med f.høyre som rot.
*/

        if(p.forelder == null) return null;  //Treet har bare en node
        else if(p.forelder.høyre == p) return p.forelder;    //p sin forelder er neste i postorden
        else if(p.forelder.høyre == null) return p.forelder;  //p er enebarn
        else{
            p = p.forelder.høyre;  //Hopper over i subtreet
            while (p.venstre != null){
                p = p.venstre;   //Følger ventre barn til venstre hvis p er enebarn helt til p.venstre er null
            }
            while(p.høyre != null){
                p = p.høyre;  //Følger høyre barn til høyre hvis p er enebarn helt til p.høyre er null
            }
        }
        return p;  //returnerer noden
    }

    public void postorden(Oppgave<? super T> oppgave) {
        //throw new UnsupportedOperationException("Ikke kodet ennå!");
        Node<T> p = rot;  //p starter i rot-noden
        postordenRecursive(p, oppgave);  //Kaller rekursiv metode
        while(p != null){
            p = nestePostorden(p);  //Flytter p til neste til p == null
        }
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        //throw new UnsupportedOperationException("Ikke kodet ennå!");

        if (p.venstre != null) postordenRecursive(p.venstre,oppgave);
        if (p.høyre != null) postordenRecursive(p.høyre,oppgave);
        oppgave.utførOppgave(p.verdi);
    }

    public ArrayList<T> serialize() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }
} // ObligSBinTre
