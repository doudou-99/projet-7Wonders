package modele;

import modele.dao.BaseMongo;

public class GestionTour {
    private int tourEnCours;
    private Tour tour;
    private Age age;

    public GestionTour(Tour tour){
        this.tour=tour;
        this.tourEnCours=this.tour.getNombreTourEnCours();
    }

    public void passageAgeSuivant(){
        while (this.tour.getNombreTourEnCours()<tour.getAge().getNombreTour()) {
             tourEnCours+= 1;
        }
        if (this.tourEnCours >= this.tour.getAge().getNombreTour() && this.tour.getAge() == BaseMongo.getBase().getAges().get(0)){
            this.tour.setAge(BaseMongo.getBase().getAges().get(1));
            this.tour.setNombreTourEnCours(1);
        }
        if (this.tourEnCours >= this.tour.getAge().getNombreTour() && this.tour.getAge() == BaseMongo.getBase().getAges().get(1)){
            this.tour.setAge(BaseMongo.getBase().getAges().get(2));
            this.tour.setNombreTourEnCours(1);
        }
    }

    public int getTourEnCours() {
        return tourEnCours;
    }

    public void setTourEnCours(int tourEnCours) {
        this.tourEnCours = tourEnCours;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public Age getAge() {
        return age;
    }

    public void setAge(Age age) {
        this.age = age;
    }
}
