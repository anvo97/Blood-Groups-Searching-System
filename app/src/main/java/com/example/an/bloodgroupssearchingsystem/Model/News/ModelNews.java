package com.example.an.bloodgroupssearchingsystem.Model.News;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class ModelNews {
    private LoadNewsLister loadNewsLister;
    private DatabaseReference mDatabase;
    public ModelNews(LoadNewsLister loadNewsLister){
        this.loadNewsLister=loadNewsLister;
    }
    public void getDataNews(){
        ArrayList<News> listNews=new ArrayList<>();
        listNews.add(new News("1","1 Batman v Superman","05:56 20/01/2019","https://firebasestorage.googleapis.com/v0/b/blood-groups-searching-s-6867f.appspot.com/o/bvs_bg.jpg?alt=media&token=b2e76f2e-b130-4b91-ae50-cf48ac19402a"));
        listNews.add(new News("2","2 Batman v Superman","05:56 20/01/2019","https://firebasestorage.googleapis.com/v0/b/blood-groups-searching-s-6867f.appspot.com/o/bvs_bg.jpg?alt=media&token=b2e76f2e-b130-4b91-ae50-cf48ac19402a"));
        listNews.add(new News("3","3 Batman v Superman","05:56 20/01/2019","https://firebasestorage.googleapis.com/v0/b/blood-groups-searching-s-6867f.appspot.com/o/bvs_bg.jpg?alt=media&token=b2e76f2e-b130-4b91-ae50-cf48ac19402a"));
        listNews.add(new News("4","4 Batman v Superman","05:56 20/01/2019","https://firebasestorage.googleapis.com/v0/b/blood-groups-searching-s-6867f.appspot.com/o/bvs_bg.jpg?alt=media&token=b2e76f2e-b130-4b91-ae50-cf48ac19402a"));
        listNews.add(new News("5","5 Batman v Superman","05:56 20/01/2019","https://firebasestorage.googleapis.com/v0/b/blood-groups-searching-s-6867f.appspot.com/o/bvs_bg.jpg?alt=media&token=b2e76f2e-b130-4b91-ae50-cf48ac19402a"));
        loadNewsLister.onLoadNewsSuccess(listNews);
    }
    public void getDataDetailNews(){
        ArrayList<Detail> listDetailNews=new ArrayList<>();
        listDetailNews.add(new Detail("1","Việc đưa ra các tiêu chí về sức khỏe, hình thể không phải vì HV có quan điểm phân biệt ngoại hình của người học mà vì tính đặc thù của nghề nghiệp. Hiện nay có 4 khối cơ quan yêu cầu về sức khỏe, thể lực ngay từ khâu tuyển sinh để đào tạo, gồm: quân đội, công an, kiểm sát, tòa án. Với quân đội thì rõ rồi, ai cũng thấy yêu cầu về sức khỏe, thể lực là đương nhiên. Ngoài ra, công an, kiểm sát, tòa án là các khối cơ quan tham gia vào hoạt động tư pháp, gắn liền với các hoạt động phòng chống tội phạm, nên có tính đặc thù trong tuyển sinh và tuyển dụng. Việc các đơn vị đào tạo của các khối ngành này đưa ra tiêu chuẩn về sức khỏe, hình thể để tuyển sinh là để đáp ứng yếu tố đặc thù đó.","anh 1",null));
        listDetailNews.add(new Detail("2","Việc đưa ra các tiêu chí về sức khỏe, hình thể không phải vì HV có quan điểm phân biệt ngoại hình của người học mà vì tính đặc thù của nghề nghiệp. Hiện nay có 4 khối cơ quan yêu cầu về sức khỏe, thể lực ngay từ khâu tuyển sinh để đào tạo, gồm: quân đội, công an, kiểm sát, tòa án. Với quân đội thì rõ rồi, ai cũng thấy yêu cầu về sức khỏe, thể lực là đương nhiên. Ngoài ra, công an, kiểm sát, tòa án là các khối cơ quan tham gia vào hoạt động tư pháp, gắn liền với các hoạt động phòng chống tội phạm, nên có tính đặc thù trong tuyển sinh và tuyển dụng. Việc các đơn vị đào tạo của các khối ngành này đưa ra tiêu chuẩn về sức khỏe, hình thể để tuyển sinh là để đáp ứng yếu tố đặc thù đó.","anh 2","https://firebasestorage.googleapis.com/v0/b/blood-groups-searching-s-6867f.appspot.com/o/bvs_bg.jpg?alt=media&token=b2e76f2e-b130-4b91-ae50-cf48ac19402a"));
        listDetailNews.add(new Detail("3","Việc đưa ra các tiêu chí về sức khỏe, hình thể không phải vì HV có quan điểm phân biệt ngoại hình của người học mà vì tính đặc thù của nghề nghiệp. Hiện nay có 4 khối cơ quan yêu cầu về sức khỏe, thể lực ngay từ khâu tuyển sinh để đào tạo, gồm: quân đội, công an, kiểm sát, tòa án. Với quân đội thì rõ rồi, ai cũng thấy yêu cầu về sức khỏe, thể lực là đương nhiên. Ngoài ra, công an, kiểm sát, tòa án là các khối cơ quan tham gia vào hoạt động tư pháp, gắn liền với các hoạt động phòng chống tội phạm, nên có tính đặc thù trong tuyển sinh và tuyển dụng. Việc các đơn vị đào tạo của các khối ngành này đưa ra tiêu chuẩn về sức khỏe, hình thể để tuyển sinh là để đáp ứng yếu tố đặc thù đó.","anh 3","https://firebasestorage.googleapis.com/v0/b/blood-groups-searching-s-6867f.appspot.com/o/imgtest.jpg?alt=media&token=a01b8d37-68c8-4b54-bc96-42896a5bbab8"));
        listDetailNews.add(new Detail("4","Việc đưa ra các tiêu chí về sức khỏe, hình thể không phải vì HV có quan điểm phân biệt ngoại hình của người học mà vì tính đặc thù của nghề nghiệp. Hiện nay có 4 khối cơ quan yêu cầu về sức khỏe, thể lực ngay từ khâu tuyển sinh để đào tạo, gồm: quân đội, công an, kiểm sát, tòa án. Với quân đội thì rõ rồi, ai cũng thấy yêu cầu về sức khỏe, thể lực là đương nhiên. Ngoài ra, công an, kiểm sát, tòa án là các khối cơ quan tham gia vào hoạt động tư pháp, gắn liền với các hoạt động phòng chống tội phạm, nên có tính đặc thù trong tuyển sinh và tuyển dụng. Việc các đơn vị đào tạo của các khối ngành này đưa ra tiêu chuẩn về sức khỏe, hình thể để tuyển sinh là để đáp ứng yếu tố đặc thù đó.","anh 4","https://firebasestorage.googleapis.com/v0/b/blood-groups-searching-s-6867f.appspot.com/o/bvs_bg.jpg?alt=media&token=b2e76f2e-b130-4b91-ae50-cf48ac19402a"));
        loadNewsLister.onLoadDetailNewsSuccess(listDetailNews);
    }
}
