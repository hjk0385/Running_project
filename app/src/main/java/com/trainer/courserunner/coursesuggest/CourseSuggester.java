package com.trainer.courserunner.coursesuggest;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.trainer.courserunner.managedata.MapDAO;
import com.trainer.courserunner.managedata.MapDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CourseSuggester {
    private final List<Dot> imageDot;
    private final List<Dot> mapDot;

    public CourseSuggester(Bitmap image,
                           double startX, double startY,
                           double endX, double endY) {
        imageDot = this.loadImage(image);
        mapDot = this.loadAddress(startX, startY, endX, endY);
    }

    private List<Dot> loadImage(Bitmap image) {
        List<Dot> imageDot = new ArrayList<>();
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int rgb = image.getPixel(x, y);
                int red = Color.red(rgb);
                int green = Color.red(rgb);
                int blue = Color.red(rgb);

                //100~255인 점들만 추출(그려진 점들)(임의기준)
                if (red >= 100) {
                    imageDot.add(new DotRGB(image.getWidth(), image.getHeight(), x, y));
                }
            }
        }
        return imageDot;
    }

    //load Map
    private List<Dot> loadAddress(double startX, double startY,
                                  double endX, double endY) {
        //load data
        MapDAO mapDAO = new MapDAO();
        List<MapDTO> scopeAddress = mapDAO.getScopeAddress(startX, startY, endX, endY);
        //convert dot
        List<Dot> addressDotList = new ArrayList<Dot>();
        for (MapDTO address : scopeAddress) {
            addressDotList.add(new DotAddress(startX, startY, endX, endY, address.getX(), address.getY()));
        }
        return addressDotList;
    }

    //리스트에서 가장 가까운 점 반환
    private Dot getClosestDot(List<Dot> dotList, Dot dot) {
        Dot closestDot = dotList.get(0);
        for (int i = 1; i < dotList.size(); i++) {
            if (closestDot.getCost(dot) >
                    dotList.get(i).getCost(dot)) {
                closestDot = dotList.get(i);
            }
        }
        return closestDot;
    }

    //리스트에서 가장 가까운 주소들을 찾아서 추출
    private List<Dot> extractionDots(List<Dot> sandDotList, List<Dot> shovelDotList) {
        HashSet<Dot> extractionDotHash = new HashSet<Dot>();
        for (Dot dot : shovelDotList) {
            extractionDotHash.add(getClosestDot(sandDotList, dot));
        }
        return new ArrayList<>(extractionDotHash);
    }

    //코스 만들기
    private List<DotAddress> makeCourse(List<Dot> addressDotList) {
        List<DotAddress> addressPathList = new ArrayList<>();
        Dot currentDot = addressDotList.get(0);
        addressDotList.remove(currentDot);
        addressPathList.add((DotAddress) currentDot);
        while (addressDotList.size() != 0) {
            currentDot = getClosestDot(addressDotList, currentDot);
            addressDotList.remove(currentDot);
            addressPathList.add((DotAddress) currentDot);
        }
        return addressPathList;
    }

    //코스 제안
    public List<DotAddress> suggestPath() {
        List<Dot> addressDotList = extractionDots(this.mapDot, this.imageDot);
        return makeCourse(addressDotList);
    }
}
