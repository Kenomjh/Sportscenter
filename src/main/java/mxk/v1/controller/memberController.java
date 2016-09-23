package mxk.v1.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import mxk.v1.dao.memberDAO;
import mxk.v1.model.kReservationModel;
import mxk.v1.model.memberModel;
import mxk.v1.model.pReservationModel;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class memberController implements Initializable {
    // 버튼
    @FXML
    Button nextbtn;
    @FXML
    Button prebtn;
    @FXML
    Button next2btn;
    @FXML
    Button pre2btn;
    @FXML
    Button next3btn;
    @FXML
    Button login;
    @FXML
    Button change;
    @FXML
    Button exit;
    @FXML
    Button addrbtn;
    @FXML
    Button caddrbtn;

    // 페인
    @FXML
    Pane join1;
    @FXML
    Pane join2;
    @FXML
    Pane join3;
    @FXML
    Pane join4;
    @FXML
    TabPane memberTab;

    // join1
    @FXML
    CheckBox ok;

    // join2
    @FXML
    TextField uid;
    @FXML
    PasswordField pw;
    @FXML
    PasswordField pwok;
    @FXML
    TextField name;
    @FXML
    CheckBox wo;
    @FXML
    CheckBox ma;
    @FXML
    TextField tel;
    @FXML
    ComboBox year;
    @FXML
    ComboBox mon;
    @FXML
    ComboBox day;
    @FXML
    TextField addr1;
    @FXML
    TextField addr2;
    @FXML
    TextField addr3;
    @FXML
    TextField addr4;
    @FXML
    TextField email;
    @FXML
    ComboBox www;
    @FXML
    CheckBox yes;
    @FXML
    CheckBox no;

    // join3
    @FXML
    TextField rid;
    @FXML
    TextField rname;
    @FXML
    TextField rgen;
    @FXML
    TextField rtel;
    @FXML
    TextField rbirth;
    @FXML
    TextField raddr1;
    @FXML
    TextField raddr2;
    @FXML
    TextField raddr3;
    @FXML
    TextField raddr4;
    @FXML
    TextField remail;
    @FXML
    TextField rtalk;

    // mytab
    @FXML
    TextField cid;
    @FXML
    PasswordField cpw;
    @FXML
    PasswordField cokpw;
    @FXML
    TextField cname;
    @FXML
    CheckBox cwo;
    @FXML
    CheckBox cma;
    @FXML
    TextField ctel;
    @FXML
    TextField caddr1;
    @FXML
    TextField caddr2;
    @FXML
    TextField caddr3;
    @FXML
    TextField caddr4;
    @FXML
    TextField cmail;
    @FXML
    ChoiceBox cwww;
    @FXML
    CheckBox cyes;
    @FXML
    CheckBox cno;
    @FXML
    ChoiceBox cyear;
    @FXML
    ChoiceBox cmon;
    @FXML
    ChoiceBox cday;

    // restab
    @FXML
    TableColumn pdate;
    @FXML
    TableColumn ptime;
    @FXML
    TableColumn ppay;
    @FXML
    TableColumn tdate;
    @FXML
    TableColumn ring;
    @FXML
    TableColumn lesson;
    @FXML
    TableColumn ldate;
    @FXML
    TableColumn lcost;
    @FXML
    TableColumn lres;
    @FXML
    TableColumn lcount;
    @FXML
    TableColumn edit;

    @FXML
    TableView renttv;
    @FXML
    TableView tableView2;

    private ObservableList<pReservationModel> rentlist = null;
    // TableView에 표시할 데이터들을 저장하기 위해
    // FXCollections에서 제공하는 ObservableList 객체 정의

    private int checkAggree;
    private int checkAggree2;
    private int checkAggree3;
    private int checkAggree4;

    private String gen;
    private String msg;

    public static int mno; // 회원 가입 번호

    // 생년월일 콤보박스에 들어갈 아이템들을 저장할 변수
    private String[] years = new String[57];
    private String[] mons = new String[12];
    private String[] days = new String[31];

    // 이메일 도메인 콤보박스에 들어갈 아이템들을 저장할 변수
    private String[] wwws = new String[5];

    // 데이터 임시 저장소
    private List<memberModel> memlist;

    private Stage stage;

    private Label username;
    private Label nim;
    private Button logoutbtn;
    private Pane mainPane;

    private String cgen;
    private Button loginBtn;

    public static ObservableList<kReservationModel> krmList = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        memlist = new ArrayList();

        checkAggree = 0;
        checkAggree2 = 0;
        checkAggree3 = 0;
        checkAggree4 = 0;

        gen = "";
        msg = "";
        cgen = "";

        // 연도
        for (int i = 0; i <= 56; i++) {
            years[i] = (i + 1960) + "";
        }
        ObservableList yearl = FXCollections.observableArrayList(years);
        year.setItems(yearl);
        cyear.setItems(yearl);

        // 월
        for (int i = 0; i < 12; i++) {
            mons[i] = (i + 1) + "";
        }

        ObservableList monthl = FXCollections.observableArrayList(mons);
        mon.setItems(monthl);
        cmon.setItems(monthl);

        // 일
        for (int i = 0; i < 31; i++) {
            days[i] = (i + 1) + "";
        }

        ObservableList dayl = FXCollections.observableArrayList(days);
        day.setItems(dayl);
        cday.setItems(dayl);

        // 이메일
        wwws[0] = "naver.com";
        wwws[1] = "gmail.com";
        wwws[2] = "daum.com";
        wwws[3] = "naver.net";
        wwws[4] = "nate.com";

        ObservableList wwwl = FXCollections.observableArrayList(wwws);
        www.setItems(wwwl);
        cwww.setItems(wwwl);

        if (mainController.mlm != null) {  // 쌤 수정!

        pdate.setCellValueFactory(new PropertyValueFactory<pReservationModel, String>("pdate"));
        ptime.setCellValueFactory(new PropertyValueFactory<pReservationModel, String>("ptime"));
        ppay.setCellValueFactory(new PropertyValueFactory<pReservationModel, String>("ppay"));
        tdate.setCellValueFactory(new PropertyValueFactory<pReservationModel, String>("tdate"));
        ring.setCellValueFactory(new PropertyValueFactory<pReservationModel, String>("ring"));

        rentlist = FXCollections.observableArrayList();

        System.out.println( "mc init" );

            List<pReservationModel> rds = memberDAO.listRent(mainController.mlm);
            // board 테이블의 내용을 ArrayList 배열로 넘김
            for (pReservationModel m : rds) {
                // 배열에 저장된 게시판 글목록에서 글(행)을 하나씩 읽어와서
                // observableList 에 저장
                rentlist.add(m);
            }


        renttv.setItems(rentlist);

        lesson.setCellValueFactory(new PropertyValueFactory<kReservationModel,String>("lesson"));
        ldate.setCellValueFactory(new PropertyValueFactory<kReservationModel,String>("term"));
        lcost.setCellValueFactory(new PropertyValueFactory<kReservationModel,String>("lpay"));
        lres.setCellValueFactory(new PropertyValueFactory<kReservationModel,String>("tday"));
        lcount.setCellValueFactory(new PropertyValueFactory<kReservationModel,String>("ing"));
        //lcount1;--버튼용

        krmList = FXCollections.observableArrayList();


            List<kReservationModel> km = memberDAO.createkrm();
            for (kReservationModel m : km) {
                //배열에 저장된 게시판 글목록에서 글(행)을 하나씩 읽어와서 ObservableList 에 저장
                krmList.add(m);
            }



        tableView2.setItems(krmList);
        }

    } // initialize

    public void ckidid(ActionEvent actionEvent){
        memberDAO md = new memberDAO();
        int cno = md.idchkdb(uid.getText());
        if(cno==0){
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("확인창");
            info.setHeaderText(null);
            info.setContentText("사용가능한 아이디 입니다.");
            info.showAndWait();
        }else{
            Alert info = new Alert(Alert.AlertType.WARNING);
            info.setTitle("확인창");
            info.setHeaderText(null);
            info.setContentText("사용 불가능한 아이디 입니다.");
            info.showAndWait();
        }
    }

    public void updateR(ActionEvent actionEvent) throws Exception {

        int num = renttv.getSelectionModel().getSelectedIndex();

        if(num != -1) {
            String ring = rentlist.get(num).getRing();
            if (!ring.equals("예약취소")) {

                FXMLLoader f = new FXMLLoader(getClass().getResource("/fxml/editRent.fxml"));
                Parent root = f.load();

                Stage stage = new Stage();
                stage.setScene(new Scene(root));

                String prno = rentlist.get(num).getPrno();
                String y1 = rentlist.get(num).getPtime();
                String y2 = rentlist.get(num).getPdate();
                String y3 = rentlist.get(num).getPpay();
                editRController erc = f.getController();
                erc.startEditr(prno, y1, y2, y3);

                stage.showAndWait();

                rentlist.clear();

                List<pReservationModel> km = memberDAO.listRent(mainController.mlm);
                for (pReservationModel m : km) {
                    rentlist.add(m);
                }

                renttv.setItems(rentlist);
            } else {
                Alert warn = new Alert(Alert.AlertType.WARNING);
                warn.setTitle(":: 변경 불가 일정 ::");
                warn.setHeaderText(null);
                warn.setContentText("이미 취소된 예약일정입니다.");
                warn.showAndWait();
            }
        }else {
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("확인");
            info.setHeaderText(null);
            info.setContentText("수정/취소 할 예약정보를 선택해주세요");
            info.showAndWait();
        }

    }

    public void updateK(ActionEvent event) {
        int num = tableView2.getSelectionModel().getSelectedIndex();

        if (num != -1) {
            String ing = krmList.get(num).getIng();
            if (!ing.equals("예약취소")) {

                FXMLLoader f = new FXMLLoader(getClass().getResource("/fxml/editKangseup.fxml"));
                Parent root = null;
                try {
                    root = f.load();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Stage stage = new Stage();
                stage.setScene(new Scene(root));


                String caddr[] = memberController.krmList.get(num).getLesson().toString().split("-");

                String rno = krmList.get(num).getRno();
                String y1 = caddr[0];
                String y2 = caddr[1];
                String y3 = memberController.krmList.get(num).getTerm();
                String y4 = memberController.krmList.get(num).getLpay();

                editKangseupController ekc = f.getController();

                ekc.startEditk(rno, y1, y2, y3, y4);

                stage.showAndWait();


                krmList.clear();

                List<kReservationModel> km = memberDAO.createkrm();
                for (kReservationModel m : km) {
                    krmList.add(m);
                }

                tableView2.setItems(krmList);
            }else {
                Alert warn = new Alert(Alert.AlertType.WARNING);
                warn.setTitle(":: 변경 불가 일정 ::");
                warn.setHeaderText(null);
                warn.setContentText("이미 취소된 예약일정입니다.");
                warn.showAndWait();
            }
        } else {
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("확인");
            info.setHeaderText(null);
            info.setContentText("수정/취소 할 예약정보를 선택해주세요");
            info.showAndWait();
        }

    }

    // 회원가입 첫 페이지
    public void showfirst(Event e) {
        join1.setVisible(true);
        join2.setVisible(false);
        join3.setVisible(false);
        join4.setVisible(false);
    }// showfirst

    // 버튼 사라지게하기 - 동의하면 다시 나타나게
    public void showbtn(ActionEvent ae) {
        if (checkAggree == 1) {
            nextbtn.setDisable(true);
            checkAggree = 0;
        } else {
            nextbtn.setDisable(false);
            checkAggree = 1;
        }
    } // showbtn

    // 성별 - 정리
    public void chwo(ActionEvent event) {
        if (checkAggree2 == 0) gen = "여성";
        else gen = "";
    } // chwo

    // 성별 수정
    public void chwo2(ActionEvent event) {
        if (checkAggree4 == 0) gen = "여성";
        else cgen = "";
    } // chwo

    public void chma(ActionEvent event) {
        if (checkAggree2 == 0) gen = "남성";
        else gen = "";
    } // chma

    public void chma2(ActionEvent event) {
        if (checkAggree4 == 0) gen = "남성";
        else cgen = "";
    } // chma

    // 다음으로 - 두번째 화면으로
    public void showsecond(ActionEvent event) throws Exception {
        join1.setVisible(false);
        join2.setVisible(true);
        join3.setVisible(false);
        join4.setVisible(false);
    } // showsecond

    // 이전으로 - 첫번째 화면으로
    public void backfirst(ActionEvent event) {
        join1.setVisible(true);
        join2.setVisible(false);
        join3.setVisible(false);
        join4.setVisible(false);
    } // backfirst

    // 데이터 보내기
    public void sendData(List<memberModel> memlist) {
        /*this.memlist = memlist;*/

        memberModel mem = memlist.get(memlist.size() - 1);
        rid.setText(mem.getId());
        rname.setText(mem.getName());
        rgen.setText(mem.getGender());
        rtel.setText(mem.getTel());
        rbirth.setText(mem.getBday());
        raddr1.setText(mem.getAddr1());
        raddr2.setText(mem.getAddr2());
        raddr3.setText(mem.getAddr3());
        raddr4.setText(mem.getAddr4());
        remail.setText(mem.getEmail());
        rtalk.setText(mem.getMsg());

    } // sendData

    // 다음으로 - 세번쨰 화면으로 이동
    public void showthird(ActionEvent event) {
        // 새로운 회원
        String format = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String regdate = sdf.format(new Date());
        String bday = year.getSelectionModel().getSelectedItem()
                + "-" + mon.getSelectionModel().getSelectedItem() + "-" + day.getSelectionModel().getSelectedItem();

        String emailA = email.getText() + "@" + www.getSelectionModel().getSelectedItem();

        if (uid.getText().equals("")) {
            showWarn("아이디를 입력하세요!!");
            uid.requestFocus();
        } else if (pw.getText().equals("")) {
            showWarn("비밀번호를 입력하세요!!");
            pw.requestFocus();
        } else if (!pw.getText().equals(pwok.getText())) {
            showWarn("비밀번호가 동일하지 않습니다!!");
        } else if (name.getText().equals("")) {
            showWarn("이름을 입력하세요!!");
            name.requestFocus();
        } else if (gen.equals("")) {
            showWarn("성별을 선택하세요!!");
        } else if (tel.getText().equals("")) {
            showWarn("전화번호를 입력하세요!!");
            tel.requestFocus();
        } else if (bday.equals("")) {
            showWarn("생년월일을 선택하세요!!");
        } else if (addr1.getText().equals("")) {
            showWarn("주소를 입력하세요!!");
            addr1.requestFocus();
        } else if (addr2.getText().equals("")) {
            showWarn("주소를 입력하세요!!");
            addr2.requestFocus();
        } else if (addr3.getText().equals("")) {
            showWarn("주소를 입력하세요!!");
            addr3.requestFocus();
        } else if (addr4.getText().equals("")) {
            showWarn("주소를 입력하세요!!");
            addr4.requestFocus();
        } else if (email.getText().equals("")) {
            showWarn("이메일을 입력하세요!!");
            email.requestFocus();
        } else if (msg.equals("")) {
            showWarn("문자수신을 선택하세요!!");
        } else {
            memlist.add(new memberModel(String.valueOf(mno += 1) + "", uid.getText(), pw.getText(), name.getText(), bday, gen,
                    addr1.getText(), addr2.getText(), addr3.getText(), addr4.getText(), tel.getText(), emailA, msg, regdate));

            sendData(memlist);

            join1.setVisible(false);
            join2.setVisible(false);
            join3.setVisible(true);
            join4.setVisible(false);
        } // if-else
    } // showthird

    // 경고창
    private void showWarn(String s) {
        Alert warn = new Alert(Alert.AlertType.WARNING);
        warn.setTitle("오류!!");
        warn.setHeaderText(null);
        warn.setContentText(s);
        warn.showAndWait();
    }

    // 문자수신
    public void chyes(ActionEvent event) {
        if (checkAggree3 == 0) msg = "yes";
        else msg = "";
    } // chyes

    public void chno(ActionEvent event) {
        if (checkAggree3 == 0) msg = "no";
        else msg = "";
    } // chno

    // 우편번호 찾기
    public void showaddr(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addr.fxml"));
        Parent root = loader.load();

        addrController ac = loader.getController();


        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("주소");
        ac.sendData(addr1, addr2, addr3, stage);
        stage.show();


    } // showaddr

    public void showaddr2(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addr.fxml"));
        Parent root = loader.load();

        addrController ac = loader.getController();


        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("주소");
        ac.sendData(caddr1, caddr2, caddr3, stage);
        stage.show();
    } // showaddr2

    // 이전으로 - 두번째 화면으로 이동
    public void backsecond(ActionEvent event) {
        join1.setVisible(false);
        join2.setVisible(true);
        join3.setVisible(false);
        join4.setVisible(false);
    } // backsecond

    // 다음으로 - 네번째 화면으로 이동
    public void showfourth(ActionEvent event) {
        join1.setVisible(false);
        join2.setVisible(false);
        join3.setVisible(false);
        join4.setVisible(true);

        String format = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String regdate = sdf.format(new Date());
        String bday = year.getSelectionModel().getSelectedItem()
                + "-" + mon.getSelectionModel().getSelectedItem() + "-" + day.getSelectionModel().getSelectedItem();

        String emailA = email.getText() + "@" + www.getSelectionModel().getSelectedItem();

        memberModel md = new memberModel("", uid.getText(), pw.getText(), name.getText(), bday, gen,
                addr1.getText(), addr2.getText(), addr3.getText(), addr4.getText(), tel.getText(), emailA, msg, regdate);

        memberDAO.savedb(md);
    } // showfourth

    // 로그인 창 띄우기
    public void showlogin(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("로그인");
        stage.show();

        loginController lc = loader.getController();
        lc.sendData(logoutbtn, loginBtn, username, nim, stage, mainPane);

        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/fxml/subMain.fxml"));
        Parent root2 = loader2.load();

        mainPane.getChildren().clear();
        mainPane.getChildren().add(root2);

    } // showlogin

    public void showjoin() throws Exception {

        SingleSelectionModel<Tab> sm = memberTab.getSelectionModel();
        sm.select(0);

        memberTab.getTabs().get(0).setDisable(false);
        memberTab.getTabs().get(1).setDisable(true);
        memberTab.getTabs().get(2).setDisable(true);
    } // showjoin

    public void showinfo(Event event) {

        System.out.println( "showinfo" );
        if (mainController.mlmm != null) {  // 쌤 수정!
            cid.setText(mainController.mlmm.getUserid());
            cpw.setText(mainController.mlmm.getPasswd());
            cname.setText(mainController.mlmm.getUsername());
            // 성별
            if (mainController.mlmm.getGender().equals("여성")) {
                cwo.setSelected(true);
                cgen = "여성";
            } else {
                cma.setSelected(true);
                cgen = "남성";
            }
            ctel.setText(mainController.mlmm.getTel());
            // 생년월일
            cyear.setValue(mainController.mlmm.getBday().split("-")[0]);
            cmon.setValue(mainController.mlmm.getBday().split("-")[1]);
            cday.setValue(mainController.mlmm.getBday().split("-")[2]);

            String caddr[] = mainController.mlmm.getAddr().toString().split("[ ]");
            caddr1.setText(caddr[0].substring(0, 3));
            caddr2.setText(caddr[0].substring(4, 7));
            caddr3.setText(caddr[1] + " " + caddr[2] + " " + caddr[3] + " " + caddr[4] + " " + caddr[5]);

            String etcaddr = "";
            for (int i = 6; i < caddr.length; ++i) {
                etcaddr += caddr[i] + " ";
            }
            caddr4.setText(etcaddr);
            String cmaill[] = mainController.mlmm.getEmail().toString().split("[@]");
            cmail.setText(cmaill[0]);

            // 도메인
            cwww.setValue(mainController.mlmm.getEmail().split("@")[1]);
        }
    } // showinfo

    // 수정하기
    public void update(ActionEvent event) {
        String cbday = cyear.getSelectionModel().getSelectedItem()
                + "-" + cmon.getSelectionModel().getSelectedItem() + "-" + cday.getSelectionModel().getSelectedItem();

        String cemailA = cmail.getText() + "@" + cwww.getSelectionModel().getSelectedItem();

        if (cpw.getText().equals("")) {
            showWarn("비밀번호를 입력하세요!!");
            cpw.requestFocus();
        } else if (!cpw.getText().equals(cokpw.getText())) {
            showWarn("비밀번호가 동일하지 않습니다!!");
        } /*else if (cgen.equals("")) {
            showWarn("성별을 선택하세요!!");
        }*/ else if (ctel.getText().equals("")) {
            showWarn("전화번호를 입력하세요!!");
            ctel.requestFocus();
        } else if (cbday.equals("")) {
            showWarn("생년월일을 선택하세요!!");
        } else if (caddr1.getText().equals("")) {
            showWarn("주소를 입력하세요!!");
            caddr1.requestFocus();
        } else if (caddr2.getText().equals("")) {
            showWarn("주소를 입력하세요!!");
            caddr2.requestFocus();
        } else if (caddr3.getText().equals("")) {
            showWarn("주소를 입력하세요!!");
            caddr3.requestFocus();
        } else if (caddr4.getText().equals("")) {
            showWarn("주소를 입력하세요!!");
            caddr4.requestFocus();
        } else if (cemailA.equals("")) {
            showWarn("이메일을 입력하세요!!");
            cmail.requestFocus();
        } else {
            memberModel md = new memberModel("", cid.getText(), cpw.getText(), cname.getText(), cbday, cgen,
                    caddr1.getText(), caddr2.getText(), caddr3.getText(), caddr4.getText(), ctel.getText(), cemailA, msg, "");

            memberDAO.updatedb(md);

            showOk("수정완료!!");
        } // if-else
    } // update

    private void showOk(String s) {
        Alert warn = new Alert(Alert.AlertType.INFORMATION);
        warn.setTitle("성공!!!");
        warn.setHeaderText(null);
        warn.setContentText(s);
        warn.showAndWait();
    } // showOk

    // 탈퇴
    public void out(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/out.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("탈퇴!!");
        stage.show();

        outController oc = loader.getController();
         oc.sendData(logoutbtn, loginBtn, username, nim, stage, mainPane);
        System.out.println( "mc :" + mainPane );

    } // out

    public void m1() throws Exception {
        SingleSelectionModel<Tab> sm = memberTab.getSelectionModel();
        sm.select(1);
    }

    public void m2() throws Exception {
        SingleSelectionModel<Tab> sm = memberTab.getSelectionModel();
        sm.select(2);
    }

    public void sendData(Button logoutbtn, Button loginBtn, Label username, Label nim, Stage stage, Pane mainPane) {
        this.logoutbtn = logoutbtn;
        this.loginBtn = loginBtn;
        this.username = username;
        this.nim = nim;
        this.stage = stage;
        this.mainPane = mainPane;
    } // sendData


    public Pane callPane(){
        return mainPane;
    }
} // class