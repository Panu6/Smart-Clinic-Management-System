import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * SMART CLINIC MANAGEMENT SYSTEM
 * Java Swing - Single File Version
 *
 * Compile:
 *     javac SmartClinicSystem.java
 *
 * Run:
 *     java SmartClinicSystem
 *
 * Demo Login:
 *     Admin       : admin1 / adminpass
 *     Doctor      : drsmith / docpass
 *     Receptionist: reception / recpass
 *     Nurse       : nurse1 / nursepass
 */
public class SmartClinicSystem {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                    "javax.swing.plaf.nimbus.NimbusLookAndFeel"
            );
        } catch (Exception ignored) {
        }

        SwingUtilities.invokeLater(() ->
                new LoginFrame().setVisible(true)
        );
    }
}

/* =========================================================
   THEME
   ========================================================= */

class Theme {

    static final Color PRIMARY =
            new Color(0, 121, 107);

    static final Color PRIMARY_DARK =
            new Color(0, 77, 64);

    static final Color ACCENT =
            new Color(46, 125, 50);

    static final Color BACKGROUND =
            new Color(242, 248, 249);

    static final Color CARD =
            Color.WHITE;

    static final Color TEXT =
            new Color(45, 55, 60);

    static final Color MUTED =
            new Color(100, 110, 115);

    static final Color DANGER =
            new Color(198, 40, 40);

    static final Color WARNING =
            new Color(239, 108, 0);
}

/* =========================================================
   USER MODEL
   ========================================================= */

class User {

    enum Role {
        ADMIN,
        DOCTOR,
        RECEPTIONIST,
        NURSE
    }

    private final String id;
    private final String username;
    private final String password;
    private final Role role;

    User(String id,
         String username,
         String password,
         Role role) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    String getId() {
        return id;
    }

    String getUsername() {
        return username;
    }

    Role getRole() {
        return role;
    }

    boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}

/* =========================================================
   PATIENT MODEL
   ========================================================= */

class Patient {

    String id;
    String name;
    int age;
    String gender;
    String phone;
    String address;
    String complaint;

    Patient(String id,
            String name,
            int age,
            String gender,
            String phone,
            String address,
            String complaint) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.complaint = complaint;
    }
}

/* =========================================================
   APPOINTMENT MODEL
   ========================================================= */

class Appointment {

    String id;
    String patientId;
    String doctor;
    String date;
    String time;
    String reason;
    String status;

    Appointment(String id,
                String patientId,
                String doctor,
                String date,
                String time,
                String reason,
                String status) {

        this.id = id;
        this.patientId = patientId;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
        this.reason = reason;
        this.status = status;
    }
}

/* =========================================================
   VITAL RECORD MODEL
   ========================================================= */

class VitalRecord {

    String patientId;
    String date;
    String temperature;
    String bp;
    String pulse;
    String weight;
    String notes;

    VitalRecord(String patientId,
                String date,
                String temperature,
                String bp,
                String pulse,
                String weight,
                String notes) {

        this.patientId = patientId;
        this.date = date;
        this.temperature = temperature;
        this.bp = bp;
        this.pulse = pulse;
        this.weight = weight;
        this.notes = notes;
    }
}

/* =========================================================
   CONSULTATION MODEL
   ========================================================= */

class Consultation {

    String patientId;
    String doctor;
    String date;
    String symptoms;
    String diagnosis;
    String prescription;
    String notes;

    Consultation(String patientId,
                 String doctor,
                 String date,
                 String symptoms,
                 String diagnosis,
                 String prescription,
                 String notes) {

        this.patientId = patientId;
        this.doctor = doctor;
        this.date = date;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.notes = notes;
    }
}

/* =========================================================
   BILL MODEL
   ========================================================= */

class Bill {

    String id;
    String patientId;
    String date;
    String service;
    double amount;
    String status;

    Bill(String id,
         String patientId,
         String date,
         String service,
         double amount,
         String status) {

        this.id = id;
        this.patientId = patientId;
        this.date = date;
        this.service = service;
        this.amount = amount;
        this.status = status;
    }
}

/* =========================================================
   CLINIC DATA
   ========================================================= */

class ClinicData {

    static final Map<String, Patient> patients =
            new LinkedHashMap<>();

    static final java.util.List<Appointment> appointments =
            new ArrayList<>();

    static final java.util.List<VitalRecord> vitals =
            new ArrayList<>();

    static final java.util.List<Consultation> consultations =
            new ArrayList<>();

    static final java.util.List<Bill> bills =
            new ArrayList<>();

    private static int patientNo = 1001;
    private static int appointmentNo = 2001;
    private static int billNo = 3001;

    static {

        /* Sample Patients */

        patients.put(
                "P1001",
                new Patient(
                        "P1001",
                        "Alice Johnson",
                        29,
                        "Female",
                        "9876543210",
                        "Mumbai",
                        "Fever and headache"
                )
        );

        patients.put(
                "P1002",
                new Patient(
                        "P1002",
                        "Rahul Patil",
                        41,
                        "Male",
                        "9823456781",
                        "Thane",
                        "Cough and cold"
                )
        );

        patients.put(
                "P1003",
                new Patient(
                        "P1003",
                        "Sneha Shah",
                        34,
                        "Female",
                        "9765432109",
                        "Navi Mumbai",
                        "Back pain"
                )
        );

        /* Sample Appointments */

        appointments.add(
                new Appointment(
                        "A2001",
                        "P1001",
                        "Dr. Smith",
                        today(),
                        "10:00 AM",
                        "General consultation",
                        "Scheduled"
                )
        );

        appointments.add(
                new Appointment(
                        "A2002",
                        "P1002",
                        "Dr. Smith",
                        today(),
                        "11:00 AM",
                        "Follow-up",
                        "Checked-in"
                )
        );

        appointments.add(
                new Appointment(
                        "A2003",
                        "P1003",
                        "Dr. Mehta",
                        today(),
                        "12:30 PM",
                        "Pain management",
                        "Scheduled"
                )
        );

        /* Sample Vitals */

        vitals.add(
                new VitalRecord(
                        "P1001",
                        today(),
                        "98.6 F",
                        "120/80",
                        "74",
                        "62 kg",
                        "Normal"
                )
        );

        /* Sample Bill */

        bills.add(
                new Bill(
                        "B3001",
                        "P1001",
                        today(),
                        "Consultation",
                        500,
                        "Paid"
                )
        );
    }

    static String today() {

        return new SimpleDateFormat(
                "dd-MM-yyyy"
        ).format(new Date());
    }

    static String nextPatientId() {

        return "P" + (++patientNo);
    }

    static String nextAppointmentId() {

        return "A" + (++appointmentNo);
    }

    static String nextBillId() {

        return "B" + (++billNo);
    }

    static Patient patient(String id) {

        return patients.get(id);
    }

    static String patientName(String id) {

        Patient p = patients.get(id);

        return p == null ? id : p.name;
    }
}

/* =========================================================
   LOGIN SERVICE
   ========================================================= */

class LoginService {

    private final Map<String, User> users =
            new HashMap<>();

    LoginService() {

        users.put(
                "admin1",
                new User(
                        "U001",
                        "admin1",
                        "adminpass",
                        User.Role.ADMIN
                )
        );

        users.put(
                "drsmith",
                new User(
                        "U002",
                        "drsmith",
                        "docpass",
                        User.Role.DOCTOR
                )
        );

        users.put(
                "reception",
                new User(
                        "U003",
                        "reception",
                        "recpass",
                        User.Role.RECEPTIONIST
                )
        );

        users.put(
                "nurse1",
                new User(
                        "U004",
                        "nurse1",
                        "nursepass",
                        User.Role.NURSE
                )
        );
    }

    User authenticate(
            String username,
            String password) {

        User user = users.get(
                username.trim()
        );

        if (user != null &&
                user.checkPassword(password)) {

            return user;
        }

        return null;
    }
}

/* =========================================================
   LOGIN FRAME
   ========================================================= */

class LoginFrame extends JFrame {

    private final JTextField username =
            new JTextField(18);

    private final JPasswordField password =
            new JPasswordField(18);

    private final JLabel message =
            new JLabel(
                    "Enter your credentials to continue",
                    SwingConstants.CENTER
            );

    private final LoginService service =
            new LoginService();

    LoginFrame() {

        setTitle(
                "Smart Clinic Management System - Login"
        );

        setSize(480, 400);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        getContentPane()
                .setBackground(Theme.BACKGROUND);

        build();
    }

    private void build() {

        JPanel root =
                new JPanel(
                        new BorderLayout(
                                15,
                                15
                        )
                );

        root.setOpaque(false);

        root.setBorder(
                new EmptyBorder(
                        25,
                        35,
                        25,
                        35
                )
        );

        JLabel title =
                new JLabel(
                        "SMART CLINIC MANAGEMENT SYSTEM",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        21
                )
        );

        title.setForeground(
                Theme.PRIMARY_DARK
        );

        root.add(
                title,
                BorderLayout.NORTH
        );

        JPanel card =
                new JPanel(
                        new GridBagLayout()
                );

        card.setBackground(Color.WHITE);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 225, 225)
                        ),
                        new EmptyBorder(
                                20,
                                25,
                                20,
                                25
                        )
                )
        );

        GridBagConstraints g =
                new GridBagConstraints();

        g.insets =
                new Insets(
                        8,
                        8,
                        8,
                        8
                );

        g.fill =
                GridBagConstraints.HORIZONTAL;

        g.gridx = 0;
        g.gridy = 0;

        card.add(
                new JLabel("Username"),
                g
        );

        g.gridx = 1;

        card.add(
                username,
                g
        );

        g.gridx = 0;
        g.gridy = 1;

        card.add(
                new JLabel("Password"),
                g
        );

        g.gridx = 1;

        card.add(
                password,
                g
        );

        g.gridx = 0;
        g.gridy = 2;

        g.gridwidth = 2;

        message.setForeground(
                Theme.MUTED
        );

        card.add(
                message,
                g
        );

        JButton login =
                button(
                        "LOGIN",
                        Theme.PRIMARY
                );

        g.gridy = 3;

        card.add(
                login,
                g
        );

        JLabel demo =
                new JLabel(
                        "<html><center>"
                                + "Demo Logins:<br>"
                                + "Admin: admin1 / adminpass<br>"
                                + "Doctor: drsmith / docpass<br>"
                                + "Reception: reception / recpass<br>"
                                + "Nurse: nurse1 / nursepass"
                                + "</center></html>",
                        SwingConstants.CENTER
                );

        demo.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );

        demo.setForeground(
                Theme.MUTED
        );

        g.gridy = 4;

        card.add(
                demo,
                g
        );

        login.addActionListener(
                e -> doLogin()
        );

        password.addActionListener(
                e -> doLogin()
        );

        root.add(
                card,
                BorderLayout.CENTER
        );

        add(root);

        username.setText("reception");
        password.setText("recpass");
    }

    private void doLogin() {

        User user =
                service.authenticate(
                        username.getText(),
                        new String(
                                password.getPassword()
                        )
                );

        if (user == null) {

            message.setText(
                    "Invalid username or password"
            );

            message.setForeground(
                    Theme.DANGER
            );

            password.setText("");

            return;
        }

        dispose();

        new DashboardFrame(
                user
        ).setVisible(true);
    }

    static JButton button(
            String text,
            Color color) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        button.setForeground(
                Color.WHITE
        );

        button.setBackground(
                color
        );

        button.setFocusPainted(false);

        button.setOpaque(true);

        return button;
    }
}

/* =========================================================
   DASHBOARD FRAME
   ========================================================= */

class DashboardFrame extends JFrame {

    private final User user;

    private final JPanel content =
            new JPanel(
                    new CardLayout()
            );

    private JLabel status;

    private PatientPanel patientPanel;
    private AppointmentPanel appointmentPanel;
    private VitalPanel vitalPanel;
    private ConsultationPanel consultationPanel;
    private BillingPanel billingPanel;

    DashboardFrame(User user) {

        this.user = user;

        setTitle(
                "Smart Clinic Management System - "
                        + user.getRole()
        );

        setSize(
                1180,
                720
        );

        setMinimumSize(
                new Dimension(
                        1000,
                        650
                )
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        build();
    }

    private void build() {

        setLayout(
                new BorderLayout()
        );

        /* HEADER */

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setBackground(
                Theme.PRIMARY_DARK
        );

        header.setBorder(
                new EmptyBorder(
                        15,
                        20,
                        15,
                        20
                )
        );

        JLabel title =
                new JLabel(
                        "SMART CLINIC  |  "
                                + user.getRole()
                                + " PORTAL"
                );

        title.setForeground(
                Color.WHITE
        );

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        20
                )
        );

        header.add(
                title,
                BorderLayout.WEST
        );

        JLabel who =
                new JLabel(
                        "Logged in: "
                                + user.getUsername()
                );

        who.setForeground(
                Color.WHITE
        );

        header.add(
                who,
                BorderLayout.EAST
        );

        add(
                header,
                BorderLayout.NORTH
        );

        /* NAVIGATION */

        add(
                createNav(),
                BorderLayout.WEST
        );

        /* MODULES */

        patientPanel =
                new PatientPanel(
                        this,
                        user
                );

        appointmentPanel =
                new AppointmentPanel(
                        this,
                        user
                );

        vitalPanel =
                new VitalPanel(
                        this,
                        user
                );

        consultationPanel =
                new ConsultationPanel(
                        this,
                        user
                );

        billingPanel =
                new BillingPanel(
                        this,
                        user
                );

        content.setBackground(
                Theme.BACKGROUND
        );

        content.setBorder(
                new EmptyBorder(
                        12,
                        12,
                        12,
                        12
                )
        );

        content.add(
                createHome(),
                "Home"
        );

        content.add(
                patientPanel,
                "Patients"
        );

        content.add(
                appointmentPanel,
                "Appointments"
        );

        content.add(
                vitalPanel,
                "Vitals"
        );

        content.add(
                consultationPanel,
                "Consultations"
        );

        content.add(
                billingPanel,
                "Billing"
        );

        add(
                content,
                BorderLayout.CENTER
        );

        status =
                new JLabel(
                        "Ready"
                );

        status.setBorder(
                new EmptyBorder(
                        5,
                        10,
                        5,
                        10
                )
        );

        status.setForeground(
                Theme.MUTED
        );

        add(
                status,
                BorderLayout.SOUTH
        );
    }

    private JPanel createNav() {

        JPanel panel =
                new JPanel();

        panel.setBackground(
                Color.WHITE
        );

        panel.setBorder(
                new EmptyBorder(
                        15,
                        10,
                        15,
                        10
                )
        );

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel logo =
                new JLabel(
                        "  CLINIC MENU  "
                );

        logo.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        logo.setForeground(
                Theme.PRIMARY_DARK
        );

        logo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panel.add(logo);

        panel.add(
                Box.createVerticalStrut(
                        15
                )
        );

        addNav(
                panel,
                "Dashboard",
                "Home",
                true
        );

        addNav(
                panel,
                "Patient Records",
                "Patients",
                can("Patients")
        );

        addNav(
                panel,
                "Appointments",
                "Appointments",
                can("Appointments")
        );

        addNav(
                panel,
                "Vitals",
                "Vitals",
                can("Vitals")
        );

        addNav(
                panel,
                "Consultations",
                "Consultations",
                can("Consultations")
        );

        addNav(
                panel,
                "Billing",
                "Billing",
                can("Billing")
        );

        panel.add(
                Box.createVerticalGlue()
        );

        JButton logout =
                LoginFrame.button(
                        "LOGOUT",
                        Theme.DANGER
                );

        logout.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        logout.setMaximumSize(
                new Dimension(
                        190,
                        40
                )
        );

        logout.addActionListener(
                e -> {

                    int choice =
                            JOptionPane.showConfirmDialog(
                                    this,
                                    "Logout from the system?",
                                    "Confirm Logout",
                                    JOptionPane.YES_NO_OPTION
                            );

                    if (choice ==
                            JOptionPane.YES_OPTION) {

                        dispose();

                        new LoginFrame()
                                .setVisible(true);
                    }
                }
        );

        panel.add(logout);

        return panel;
    }

    private boolean can(
            String page) {

        if (user.getRole() ==
                User.Role.ADMIN) {

            return true;
        }

        if (page.equals("Patients")) {

            return true;
        }

        if (page.equals("Appointments")) {

            return user.getRole() !=
                    User.Role.NURSE;
        }

        if (page.equals("Vitals")) {

            return user.getRole() ==
                    User.Role.DOCTOR
                    ||
                    user.getRole() ==
                            User.Role.NURSE;
        }

        if (page.equals("Consultations")) {

            return user.getRole() ==
                    User.Role.DOCTOR;
        }

        if (page.equals("Billing")) {

            return user.getRole() ==
                    User.Role.RECEPTIONIST;
        }

        return false;
    }

    private void addNav(
            JPanel panel,
            String text,
            String card,
            boolean enabled) {

        JButton button =
                new JButton(text);

        button.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        button.setMaximumSize(
                new Dimension(
                        190,
                        42
                )
        );

        button.setEnabled(
                enabled
        );

        button.addActionListener(
                e -> show(card)
        );

        panel.add(button);

        panel.add(
                Box.createVerticalStrut(
                        7
                )
        );
    }

    void show(
            String card) {

        CardLayout layout =
                (CardLayout)
                        content.getLayout();

        layout.show(
                content,
                card
        );

        status.setText(
                card
                        + " module opened"
        );

        refreshAll();
    }

    void refreshAll() {

        if (patientPanel != null)
            patientPanel.refresh();

        if (appointmentPanel != null)
            appointmentPanel.refresh();

        if (vitalPanel != null)
            vitalPanel.refresh();

        if (consultationPanel != null)
            consultationPanel.refresh();

        if (billingPanel != null)
            billingPanel.refresh();
    }

    private JPanel createHome() {

        JPanel panel =
                base(
                        "Clinic Dashboard"
                );

        JPanel cards =
                new JPanel(
                        new GridLayout(
                                2,
                                3,
                                15,
                                15
                        )
                );

        cards.setOpaque(false);

        addStat(
                cards,
                "Total Patients",
                String.valueOf(
                        ClinicData.patients.size()
                )
        );

        addStat(
                cards,
                "Appointments",
                String.valueOf(
                        ClinicData.appointments.size()
                )
        );

        addStat(
                cards,
                "Vitals Recorded",
                String.valueOf(
                        ClinicData.vitals.size()
                )
        );

        addStat(
                cards,
                "Consultations",
                String.valueOf(
                        ClinicData.consultations.size()
                )
        );

        addStat(
                cards,
                "Bills",
                String.valueOf(
                        ClinicData.bills.size()
                )
        );

        addStat(
                cards,
                "System Status",
                "ACTIVE"
        );

        panel.add(
                cards,
                BorderLayout.NORTH
        );

        JTextArea information =
                new JTextArea(
                        "Welcome to Smart Clinic Management System.\n\n"
                                + "Use the left menu according to your role.\n\n"
                                + "The system supports:\n"
                                + "• Patient registration and search\n"
                                + "• Appointment scheduling\n"
                                + "• Patient check-in\n"
                                + "• Vital recording\n"
                                + "• Doctor consultations\n"
                                + "• Billing and payment tracking\n\n"
                                + "Current date: "
                                + ClinicData.today()
                );

        information.setEditable(false);

        information.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        16
                )
        );

        information.setBackground(
                Color.WHITE
        );

        information.setBorder(
                new EmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        panel.add(
                information,
                BorderLayout.CENTER
        );

        return panel;
    }

    private void addStat(
            JPanel parent,
            String title,
            String value) {

        JPanel card =
                new JPanel(
                        new BorderLayout()
                );

        card.setBackground(
                Color.WHITE
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        225,
                                        225
                                )
                        ),
                        new EmptyBorder(
                                15,
                                15,
                                15,
                                15
                        )
                )
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setForeground(
                Theme.MUTED
        );

        JLabel valueLabel =
                new JLabel(value);

        valueLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        28
                )
        );

        valueLabel.setForeground(
                Theme.PRIMARY
        );

        card.add(
                titleLabel,
                BorderLayout.NORTH
        );

        card.add(
                valueLabel,
                BorderLayout.CENTER
        );

        parent.add(card);
    }

    static JPanel base(
            String title) {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        panel.setBackground(
                Theme.BACKGROUND
        );

        JLabel heading =
                new JLabel(title);

        heading.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        24
                )
        );

        heading.setForeground(
                Theme.PRIMARY_DARK
        );

        heading.setBorder(
                new EmptyBorder(
                        5,
                        5,
                        10,
                        5
                )
        );

        panel.add(
                heading,
                BorderLayout.NORTH
        );

        return panel;
    }

    static void info(
            Component parent,
            String message) {

        JOptionPane.showMessageDialog(
                parent,
                message,
                "Smart Clinic",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    static void error(
            Component parent,
            String message) {

        JOptionPane.showMessageDialog(
                parent,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}

/* =========================================================
   COMMON MODULE PANEL
   ========================================================= */

abstract class ModulePanel
        extends JPanel {

    final DashboardFrame frame;
    final User user;

    final DefaultTableModel model;

    ModulePanel(
            DashboardFrame frame,
            User user,
            String[] columns) {

        this.frame = frame;
        this.user = user;

        setBackground(
                Theme.BACKGROUND
        );

        setLayout(
                new BorderLayout(
                        10,
                        10
                )
        );

        model =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };
    }

    JTable table() {

        JTable table =
                new JTable(model);

        table.setRowHeight(28);

        table.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        table.getTableHeader()
                .setFont(
                        new Font(
                                "SansSerif",
                                Font.BOLD,
                                12
                        )
                );

        return table;
    }

    JPanel toolbar(
            JButton... buttons) {

        JPanel panel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT
                        )
                );

        panel.setOpaque(false);

        for (JButton button : buttons) {

            panel.add(button);
        }

        return panel;
    }

    JButton b(String text) {

        return LoginFrame.button(
                text,
                Theme.PRIMARY
        );
    }

    abstract void refresh();
}

/* =========================================================
   PATIENT MODULE
   ========================================================= */

class PatientPanel
        extends ModulePanel {

    JTable table;

    JTextField search =
            new JTextField(18);

    PatientPanel(
            DashboardFrame frame,
            User user) {

        super(
                frame,
                user,
                new String[]{
                        "ID",
                        "Name",
                        "Age",
                        "Gender",
                        "Phone",
                        "Complaint"
                }
        );

        table = table();

        JPanel top =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT
                        )
                );

        top.setOpaque(false);

        top.add(
                new JLabel("Search:")
        );

        top.add(search);

        JButton searchButton =
                b("SEARCH");

        JButton addButton =
                b("REGISTER PATIENT");

        top.add(searchButton);
        top.add(addButton);

        add(
                top,
                BorderLayout.NORTH
        );

        add(
                new JScrollPane(table),
                BorderLayout.CENTER
        );

        searchButton.addActionListener(
                e -> refresh()
        );

        search.addActionListener(
                e -> refresh()
        );

        addButton.addActionListener(
                e -> register()
        );

        refresh();
    }

    void refresh() {

        model.setRowCount(0);

        String query =
                search.getText()
                        .trim()
                        .toLowerCase();

        for (Patient patient :
                ClinicData.patients.values()) {

            if (query.isEmpty()
                    ||
                    patient.id
                            .toLowerCase()
                            .contains(query)
                    ||
                    patient.name
                            .toLowerCase()
                            .contains(query)
                    ||
                    patient.phone
                            .contains(query)) {

                model.addRow(
                        new Object[]{
                                patient.id,
                                patient.name,
                                patient.age,
                                patient.gender,
                                patient.phone,
                                patient.complaint
                        }
                );
            }
        }
    }

    void register() {

        JTextField name =
                new JTextField();

        JTextField age =
                new JTextField();

        JTextField phone =
                new JTextField();

        JTextField address =
                new JTextField();

        JTextField complaint =
                new JTextField();

        JComboBox<String> gender =
                new JComboBox<>(
                        new String[]{
                                "Female",
                                "Male",
                                "Other"
                        }
                );

        Object[] fields = {

                "Name:",
                name,

                "Age:",
                age,

                "Gender:",
                gender,

                "Phone:",
                phone,

                "Address:",
                address,

                "Primary Complaint:",
                complaint
        };

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        fields,
                        "Register New Patient",
                        JOptionPane.OK_CANCEL_OPTION
                );

        if (result !=
                JOptionPane.OK_OPTION) {

            return;
        }

        try {

            int patientAge =
                    Integer.parseInt(
                            age.getText().trim()
                    );

            if (name.getText()
                    .trim()
                    .isEmpty()
                    ||
                    phone.getText()
                            .trim()
                            .isEmpty()) {

                throw new Exception();
            }

            String id =
                    ClinicData.nextPatientId();

            ClinicData.patients.put(
                    id,
                    new Patient(
                            id,
                            name.getText().trim(),
                            patientAge,
                            (String)
                                    gender.getSelectedItem(),
                            phone.getText().trim(),
                            address.getText().trim(),
                            complaint.getText().trim()
                    )
            );

            refresh();

            frame.refreshAll();

            DashboardFrame.info(
                    this,
                    "Patient registered successfully.\n"
                            + "Patient ID: "
                            + id
            );

        } catch (Exception exception) {

            DashboardFrame.error(
                    this,
                    "Please enter valid patient details."
            );
        }
    }
}

/* =========================================================
   APPOINTMENT MODULE
   ========================================================= */

class AppointmentPanel
        extends ModulePanel {

    JTable table;

    AppointmentPanel(
            DashboardFrame frame,
            User user) {

        super(
                frame,
                user,
                new String[]{
                        "ID",
                        "Patient",
                        "Doctor",
                        "Date",
                        "Time",
                        "Reason",
                        "Status"
                }
        );

        table = table();

        JButton add =
                b("NEW APPOINTMENT");

        JButton check =
                b("CHECK-IN");

        JButton complete =
                b("COMPLETE");

        add(
                toolbar(
                        add,
                        check,
                        complete
                ),
                BorderLayout.NORTH
        );

        add(
                new JScrollPane(table),
                BorderLayout.CENTER
        );

        add.addActionListener(
                e -> schedule()
        );

        check.addActionListener(
                e -> setStatus("Checked-in")
        );

        complete.addActionListener(
                e -> setStatus("Completed")
        );

        refresh();
    }

    void refresh() {

        model.setRowCount(0);

        for (Appointment appointment :
                ClinicData.appointments) {

            model.addRow(
                    new Object[]{
                            appointment.id,
                            ClinicData.patientName(
                                    appointment.patientId
                            ),
                            appointment.doctor,
                            appointment.date,
                            appointment.time,
                            appointment.reason,
                            appointment.status
                    }
            );
        }
    }

    void setStatus(
            String newStatus) {

        int row =
                table.getSelectedRow();

        if (row < 0) {

            DashboardFrame.error(
                    this,
                    "Select an appointment first."
            );

            return;
        }

        String id =
                (String)
                        model.getValueAt(
                                row,
                                0
                        );

        for (Appointment appointment :
                ClinicData.appointments) {

            if (appointment.id.equals(id)) {

                appointment.status =
                        newStatus;

                break;
            }
        }

        refresh();

        frame.refreshAll();
    }

    void schedule() {

        if (ClinicData.patients.isEmpty()) {

            DashboardFrame.error(
                    this,
                    "Register a patient first."
            );

            return;
        }

        JComboBox<String> patient =
                new JComboBox<>();

        for (Patient p :
                ClinicData.patients.values()) {

            patient.addItem(
                    p.id
                            + " - "
                            + p.name
            );
        }

        JTextField doctor =
                new JTextField(
                        "Dr. Smith"
                );

        JTextField date =
                new JTextField(
                        ClinicData.today()
                );

        JTextField time =
                new JTextField(
                        "10:00 AM"
                );

        JTextField reason =
                new JTextField();

        Object[] fields = {

                "Patient:",
                patient,

                "Doctor:",
                doctor,

                "Date (dd-MM-yyyy):",
                date,

                "Time:",
                time,

                "Reason:",
                reason
        };

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        fields,
                        "Schedule Appointment",
                        JOptionPane.OK_CANCEL_OPTION
                );

        if (result !=
                JOptionPane.OK_OPTION) {

            return;
        }

        String selected =
                (String)
                        patient.getSelectedItem();

        String patientId =
                selected.split(
                        " - "
                )[0];

        ClinicData.appointments.add(
                new Appointment(
                        ClinicData.nextAppointmentId(),
                        patientId,
                        doctor.getText(),
                        date.getText(),
                        time.getText(),
                        reason.getText(),
                        "Scheduled"
                )
        );

        refresh();

        frame.refreshAll();

        DashboardFrame.info(
                this,
                "Appointment scheduled successfully."
        );
    }
}

/* =========================================================
   VITALS MODULE
   ========================================================= */

class VitalPanel
        extends ModulePanel {

    JTable table;

    VitalPanel(
            DashboardFrame frame,
            User user) {

        super(
                frame,
                user,
                new String[]{
                        "Patient",
                        "Date",
                        "Temperature",
                        "BP",
                        "Pulse",
                        "Weight",
                        "Notes"
                }
        );

        table = table();

        JButton add =
                b("RECORD VITALS");

        add(
                toolbar(add),
                BorderLayout.NORTH
        );

        add(
                new JScrollPane(table),
                BorderLayout.CENTER
        );

        add.addActionListener(
                e -> record()
        );

        refresh();
    }

    void refresh() {

        model.setRowCount(0);

        for (VitalRecord vital :
                ClinicData.vitals) {

            model.addRow(
                    new Object[]{
                            ClinicData.patientName(
                                    vital.patientId
                            ),
                            vital.date,
                            vital.temperature,
                            vital.bp,
                            vital.pulse,
                            vital.weight,
                            vital.notes
                    }
            );
        }
    }

    void record() {

        if (ClinicData.patients.isEmpty()) {

            return;
        }

        JComboBox<String> patient =
                new JComboBox<>();

        for (Patient p :
                ClinicData.patients.values()) {

            patient.addItem(
                    p.id
                            + " - "
                            + p.name
            );
        }

        JTextField temperature =
                new JTextField();

        JTextField bloodPressure =
                new JTextField();

        JTextField pulse =
                new JTextField();

        JTextField weight =
                new JTextField();

        JTextField notes =
                new JTextField();

        Object[] fields = {

                "Patient:",
                patient,

                "Temperature:",
                temperature,

                "Blood Pressure:",
                bloodPressure,

                "Pulse:",
                pulse,

                "Weight:",
                weight,

                "Notes:",
                notes
        };

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        fields,
                        "Record Patient Vitals",
                        JOptionPane.OK_CANCEL_OPTION
                );

        if (result !=
                JOptionPane.OK_OPTION) {

            return;
        }

        String selected =
                (String)
                        patient.getSelectedItem();

        String patientId =
                selected.split(
                        " - "
                )[0];

        ClinicData.vitals.add(
                new VitalRecord(
                        patientId,
                        ClinicData.today(),
                        temperature.getText(),
                        bloodPressure.getText(),
                        pulse.getText(),
                        weight.getText(),
                        notes.getText()
                )
        );

        refresh();

        frame.refreshAll();

        DashboardFrame.info(
                this,
                "Vitals recorded successfully."
        );
    }
}

/* =========================================================
   CONSULTATION MODULE
   ========================================================= */

class ConsultationPanel
        extends ModulePanel {

    JTable table;

    ConsultationPanel(
            DashboardFrame frame,
            User user) {

        super(
                frame,
                user,
                new String[]{
                        "Patient",
                        "Doctor",
                        "Date",
                        "Symptoms",
                        "Diagnosis",
                        "Prescription",
                        "Notes"
                }
        );

        table = table();

        JButton add =
                b("NEW CONSULTATION");

        add(
                toolbar(add),
                BorderLayout.NORTH
        );

        add(
                new JScrollPane(table),
                BorderLayout.CENTER
        );

        add.addActionListener(
                e -> consult()
        );

        refresh();
    }

    void refresh() {

        model.setRowCount(0);

        for (Consultation consultation :
                ClinicData.consultations) {

            model.addRow(
                    new Object[]{
                            ClinicData.patientName(
                                    consultation.patientId
                            ),
                            consultation.doctor,
                            consultation.date,
                            consultation.symptoms,
                            consultation.diagnosis,
                            consultation.prescription,
                            consultation.notes
                    }
            );
        }
    }

    void consult() {

        if (ClinicData.patients.isEmpty()) {

            DashboardFrame.error(
                    this,
                    "Register a patient first."
            );

            return;
        }

        JComboBox<String> patient =
                new JComboBox<>();

        for (Patient p :
                ClinicData.patients.values()) {

            patient.addItem(
                    p.id
                            + " - "
                            + p.name
            );
        }

        JTextField symptoms =
                new JTextField();

        JTextField diagnosis =
                new JTextField();

        JTextField prescription =
                new JTextField();

        JTextField notes =
                new JTextField();

        Object[] fields = {

                "Patient:",
                patient,

                "Symptoms:",
                symptoms,

                "Diagnosis:",
                diagnosis,

                "Prescription:",
                prescription,

                "Clinical Notes:",
                notes
        };

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        fields,
                        "Doctor Consultation",
                        JOptionPane.OK_CANCEL_OPTION
                );

        if (result !=
                JOptionPane.OK_OPTION) {

            return;
        }

        String selected =
                (String)
                        patient.getSelectedItem();

        String patientId =
                selected.split(
                        " - "
                )[0];

        String doctorName =
                user.getUsername()
                        .equals("drsmith")
                        ? "Dr. Smith"
                        : user.getUsername();

        ClinicData.consultations.add(
                new Consultation(
                        patientId,
                        doctorName,
                        ClinicData.today(),
                        symptoms.getText(),
                        diagnosis.getText(),
                        prescription.getText(),
                        notes.getText()
                )
        );

        refresh();

        frame.refreshAll();

        DashboardFrame.info(
                this,
                "Consultation saved successfully."
        );
    }
}

/* =========================================================
   BILLING MODULE
   ========================================================= */

class BillingPanel
        extends ModulePanel {

    JTable table;

    BillingPanel(
            DashboardFrame frame,
            User user) {

        super(
                frame,
                user,
                new String[]{
                        "Bill ID",
                        "Patient",
                        "Date",
                        "Service",
                        "Amount",
                        "Status"
                }
        );

        table = table();

        JButton create =
                b("CREATE BILL");

        JButton paid =
                b("MARK PAID");

        add(
                toolbar(
                        create,
                        paid
                ),
                BorderLayout.NORTH
        );

        add(
                new JScrollPane(table),
                BorderLayout.CENTER
        );

        create.addActionListener(
                e -> createBill()
        );

        paid.addActionListener(
                e -> markPaid()
        );

        refresh();
    }

    void refresh() {

        model.setRowCount(0);

        for (Bill bill :
                ClinicData.bills) {

            model.addRow(
                    new Object[]{
                            bill.id,
                            ClinicData.patientName(
                                    bill.patientId
                            ),
                            bill.date,
                            bill.service,
                            String.format(
                                    "%.2f",
                                    bill.amount
                            ),
                            bill.status
                    }
            );
        }
    }

    void markPaid() {

        int row =
                table.getSelectedRow();

        if (row < 0) {

            DashboardFrame.error(
                    this,
                    "Select a bill first."
            );

            return;
        }

        String id =
                (String)
                        model.getValueAt(
                                row,
                                0
                        );

        for (Bill bill :
                ClinicData.bills) {

            if (bill.id.equals(id)) {

                bill.status = "Paid";

                break;
            }
        }

        refresh();
    }

    void createBill() {

        if (ClinicData.patients.isEmpty()) {

            DashboardFrame.error(
                    this,
                    "Register a patient first."
            );

            return;
        }

        JComboBox<String> patient =
                new JComboBox<>();

        for (Patient p :
                ClinicData.patients.values()) {

            patient.addItem(
                    p.id
                            + " - "
                            + p.name
            );
        }

        JTextField service =
                new JTextField(
                        "Consultation"
                );

        JTextField amount =
                new JTextField(
                        "500"
                );

        Object[] fields = {

                "Patient:",
                patient,

                "Service:",
                service,

                "Amount:",
                amount
        };

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        fields,
                        "Create Bill",
                        JOptionPane.OK_CANCEL_OPTION
                );

        if (result !=
                JOptionPane.OK_OPTION) {

            return;
        }

        try {

            double billAmount =
                    Double.parseDouble(
                            amount.getText()
                    );

            String selected =
                    (String)
                            patient.getSelectedItem();

            String patientId =
                    selected.split(
                            " - "
                    )[0];

            ClinicData.bills.add(
                    new Bill(
                            ClinicData.nextBillId(),
                            patientId,
                            ClinicData.today(),
                            service.getText(),
                            billAmount,
                            "Pending"
                    )
            );

            refresh();

            frame.refreshAll();

            DashboardFrame.info(
                    this,
                    "Bill created successfully."
            );

        } catch (Exception exception) {

            DashboardFrame.error(
                    this,
                    "Enter a valid amount."
            );
        }
    }
}