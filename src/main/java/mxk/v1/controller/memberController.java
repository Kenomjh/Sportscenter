package mxk.v1.controller;

import com.sun.prism.impl.Disposer;
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
import mxk.v1.model.memberModel;
import mxk.v1.model.pReservationModel;
import mxk.v1.model.rentalModel;

import java.io.IOException;
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
    @FXML Button prebtn;
    @FXML Button next2btn;
    @FXML Button pre2btn;
    @FXML Button next3btn;
    @FXML Button login;
    @FXML Button change;
    @FXML Button exit;
    @FXML Button addrbtn;
    @FXML Button caddrbtn;

    // 페인
    @FXML Pane join1;
    @FXML Pane join2;
    @FXML Pane join3;
    @FXML Pane join4;
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
    @FXML PasswordField pwok;
    @FXML TextField name;
    @FXML CheckBox wo;
    @FXML CheckBox ma;
    @FXML TextField tel;
    @FXML ComboBox year;
    @FXML ComboBox mon;
    @FXML ComboBox day;
    @FXML TextField addr1;
    @FXML TextField addr2;
    @FXML TextField addr3;
    @FXML TextField addr4;
    @FXML TextField email;
    @FXML ComboBox www;
    @FXML CheckBox yes;
    @FXML CheckBox no;

    // join3
    @FXML TextField rid;
    @FXML TextField rname;
    @FXML TextField rgen;
    @FXML TextField rtel;
    @FXML TextField rbirth;
    @FXML TextField raddr1;
    @FXML TextField raddr2;
    @FXML TextField raddr3;
    @FXML TextField raddr4;
    @FXML TextField remail;
    @FXML TextField rtalk;

    // mytab
    @FXML TextField cid;
    @FXML PasswordField cpw;
    @FXML PasswordField cokpw;
    @FXML TextField cname;
    @FXML CheckBox cwo;
    @FXML CheckBox cma;
    @FXML TextField ctel;
    @FXML TextField caddr1;
    @FXML TextField caddr2;
    @FXML TextField caddr3;
    @FXML TextField caddr4;
    @FXML TextField cmail;
    @FXML ChoiceBox cwww;
    @FXML CheckBox cyes;
    @FXML CheckBox cno;
    @FXML ChoiceBox cyear;
    @FXML ChoiceBox cmon;
    @FXML ChoiceBox cday;

    // restab
    @FXML TableColumn pdate;
    @FXML TableColumn ptime;
    @FXML TableColumn ppay;
    @FXML TableColumn tdate;
    @FXML TableColumn ring;
    @FXML TableColumn lesson;
    @FXML TableColumn ldate;
    @FXML TableColumn lcost;
    @FXML TableColumn lres;
    @FXML TableColumn lcount;
    @FXML TableColumn edit;

    @FXML TableView renttv;

    private ObservableList<pReservationModel> rentlist = null;
    // TableView에 표시할 데이터들을 저장하기 위해
    // FXCollections에서 제공하는 ObservableList 객체 정의

    private int checkAggree;
    private int checkAggree2;
    private int checkAggree3;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        memlist = new ArrayList();

        checkAggree =0;
        checkAggree2 =0;
        checkAggree3 =0;

        gen = "";
        msg = "";

        // 연도
        for (int i = 0; i <= 56; i++) {
            years[i] = (i + 1960) + "";
        }
        ObservableList yearl = FXCollections.observableArrayList(years);
        year.setItems(yearl);

        // 월
        for (int i = 0; i < 12; i++) {
            mons[i] = (i + 1) + "";
        }

        ObservableList monthl = FXCollections.observableArrayList(mons);
        mon.setItems(monthl);

        // 일
        for (int i = 0; i < 31; i++) {
            days[i] = (i + 1) + "";
        }

        ObservableList dayl = FXCollections.observableArrayList(days);
        day.setItems(dayl);

        // 이메일
        wwws[0] = "naver.com";
        wwws[1] = "gmail.com";
        wwws[2] = "daum.com";
        wwws[3] = "naver.net";
        wwws[4] = "nate.com";

        ObservableList wwwl = FXCollections.observableArrayList(wwws);
        www.setItems(wwwl);

        pdate.setCellValueFactory(new PropertyValueFactory<pReservationModel, String>("pdate"));
        ptime.setCellValueFactory(new PropertyValueFactory<pReservationModel, String>("ptime"));
        ppay.setCellValueFactory(new PropertyValueFactory<pReservationModel, String>("ppay"));
        tdate.setCellValueFactory(new PropertyValueFactory<pReservationModel, String>("tdate"));
        ring.setCellValueFactory(new PropertyValueFactory<pReservationModel, String>("ring"));

        rentlist = FXCollections.observableArrayList();

        List<pReservationModel> rds = memberDAO.listRent(mainController.mlm);
        // board 테이블의 내용을 ArrayList 배열로 넘김
        for (pReservationModel m : rds ) {
            // 배열에 저장된 게시판 글목록에서 글(행)을 하나씩 읽어와서
            // observableList 에 저장
            rentlist.add(m);
        }
        renttv.setItems(rentlist);

        edit.setCellFactory((param) -> new ButtonCell());


    } // initialize

    //버튼 넣기 코드
    private class ButtonCell extends TableCell<Disposer.Record, Boolean> {

        final Button cellButton2 = new Button(" 수정 / 취소 ");

        ButtonCell() {

            cellButton2.setOnAction((t) -> {

                int num = ButtonCell.this.getIndex();
                String ring = rentlist.get(num).getRing();
                if (!ring.equals("예약취소")) {

                    FXMLLoader f = new FXMLLoader(getClass().getResource("/fxml/editRent.fxml"));

                    Parent root = null;
                    try {
                        root = f.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));

                    String prno = rentlist.get(num).getPrno();

                    editRController erc = new editRController();
                    erc.sendData(prno);
                    stage.showAndWait();
                    rentlist.clear();
                    List<pReservationModel> km = memberDAO.listRent(mainController.mlm);
                    for (pReservationModel m : km) {
                        //배열에 저장된 게시판 글목록에서 글(행)을 하나씩 읽어와서 ObservableList 에 저장
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

            });

        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(cellButton2);
            }
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

        public void chma(ActionEvent event) {
            if (checkAggree2 == 0) gen = "남성";
            else gen = "";
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../main/resources/fxml/addr.fxml"));
            Parent root = loader.load();

            addrController ac = loader.getController();


            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("주소");
            ac.sendData(addr1, addr2, addr3, stage);
            stage.show();


        } // showaddr

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/login.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("로그인");
            stage.show();
        } // showlogin

        public void showinfo(Event event) {

        } // showinfo

        public void update(ActionEvent event) {
        } // update

        public void out(ActionEvent event) {
        } // out

        public void m1() throws Exception {
            SingleSelectionModel<Tab> sm = memberTab.getSelectionModel();
            sm.select(1);
        }

        public void m2() throws Exception {
            SingleSelectionModel<Tab> sm = memberTab.getSelectionModel();
            sm.select(2);
        }

} // class