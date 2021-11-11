package models.facade;

public class HomeTheaterFacade {
    // 定义各种自对象类
    private DVDPlayer dvdPlayer;
    private Screen screen;
    private Stereo stereo;

    public HomeTheaterFacade() {
        this.dvdPlayer = dvdPlayer.getInstance();
        this.screen = screen.getInstance();
        this.stereo = stereo.getInstance();
    }

    public void ready(){
        screen.down();
        dvdPlayer.on();
        stereo.off();
    }

    public void play(){
        dvdPlayer.play();
        stereo.play();
    }

    public void pause(){
        stereo.pause();
    }
    public void stop(){
        screen.up();
        dvdPlayer.off();
        stereo.off();
    }
}
