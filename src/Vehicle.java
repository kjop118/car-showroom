
public class Vehicle implements Comparable<Vehicle> {

    String marka;
    String model;
    ItemCondition stan;
    double cena;
    int rokProdkucji;
    double przebieg;
    double pojemnoscSilnika;
    int ilosc=0;
    String nazwa;

    Vehicle(String marka, String model, ItemCondition stan, double cena, int rokProdukcji, double przebieg, double pojemnoscSilnika)
    {
        this.marka = marka;
        this.model = model;
        this.stan = stan;
        this.cena = cena;
        this.rokProdkucji = rokProdukcji;
        this.przebieg = przebieg;
        this.pojemnoscSilnika = pojemnoscSilnika;
        this.nazwa=this.marka+" "+this.model+" "+this.stan+" "+this.cena+" "+this.rokProdkucji+" "+this.przebieg+" "+this.pojemnoscSilnika;
    }

    public String getMarka(){return marka;}
    public String getModel(){return model;}
    public ItemCondition getStan(){return stan;}
    public double getCena(){return cena;}
    public int getRokProdkucji(){return rokProdkucji;}
    public double getPrzebieg(){return przebieg;}
    public double getPojemnoscSilnika(){return pojemnoscSilnika;}
    public int getIlosc(){return ilosc;}
    public String getNazwa(){return nazwa;}

    public int setIlosc(int i)
    {
        this.ilosc += i;
        return this.ilosc;
    }


    public int iloscDown()
    {
        if(ilosc == 0)
            System.err.println("Brak produktu");
        ilosc--;
        return ilosc;
    }


    void print()
    {
        System.out.println("marka: "+marka);
        System.out.println("model: "+model);
        System.out.println("stan: "+stan);
        System.out.println("cena: "+cena+"zl");
        System.out.println("rok produkcji: "+rokProdkucji);
        System.out.println("przebieg: "+przebieg);
        System.out.println("pojemnosc silnika: "+pojemnoscSilnika);

    }


    @Override
    public int compareTo(Vehicle car) {
        String nazwa1=this.marka+" "+this.model+" "+this.stan+" "+this.cena+" "+this.rokProdkucji+" "+this.przebieg+" "+this.pojemnoscSilnika;
        String nazwa2=car.marka+" "+car.model+" "+car.stan+" "+car.cena+" "+car.rokProdkucji+" "+car.przebieg+" "+car.pojemnoscSilnika;
        return nazwa1.compareTo(nazwa2);
    }


}