package modeles;

import modeles.dao.BaseMongo;

public class GestionTour {
    private int tourEnCours;
    private Tour tour;

    public GestionTour(Tour tour){
        this.tour=tour;
        this.tourEnCours=1;
        this.tour.setNombreTourEnCours(tourEnCours);
        this.tour.setAge(BaseMongo.getBase().getAges().get(0));

    }

    public void passageAgeSuivant(){
        if (this.tour.getNombreTourEnCours()<tour.getAge().getNombreTour()) {
             tourEnCours+= 1;
             tour.setNombreTourEnCours(tourEnCours);
        }
        if (this.tourEnCours >= this.tour.getAge().getNombreTour() && this.tour.getAge() == BaseMongo.getBase().getAges().get(0)){
            this.tour.setAge(BaseMongo.getBase().getAges().get(1));
            this.tourEnCours=1;
            this.tour.setNombreTourEnCours(tourEnCours);
        }
        if (this.tourEnCours >= this.tour.getAge().getNombreTour() && this.tour.getAge() == BaseMongo.getBase().getAges().get(1)){
            this.tour.setAge(BaseMongo.getBase().getAges().get(2));
            this.tourEnCours=1;
            this.tour.setNombreTourEnCours(tourEnCours);
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

}
