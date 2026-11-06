
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class PortfolioApp extends JFrame {

    // Colour palette
    private final Color NAVY = new Color(43, 39, 89);
    private final Color BLUE = new Color(75, 105, 230);
    private final Color TEAL = new Color(0, 157, 143);
    private final Color CORAL = new Color(244, 112, 102);
    private final Color BG = new Color(246, 247, 253);
    private final Color INK = new Color(39, 43, 70);
    private final Color MUTED = new Color(111, 118, 143);

    private final Color[] ACCENTS = {
        BLUE, TEAL, CORAL, new Color(153, 105, 220)
    };

    private int nextAccent = 0;

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel content = new JPanel(cardLayout);
    private final JLabel pageTitle = new JLabel("Overview");

    // Spring Boot backend address
    private static final String API_URL =
            "http://localhost:8080/api/contacts";

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public PortfolioApp() {

        setTitle("Rosidha K. | Developer Portfolio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1180, 780);
        setMinimumSize(new Dimension(980, 680));
        setLocationRelativeTo(null);

        JPanel app = new JPanel(new BorderLayout());
        app.setBackground(BG);
        app.add(createSidebar(), BorderLayout.WEST);

        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(BG);
        main.add(createTopbar(), BorderLayout.NORTH);

        content.setBackground(BG);

        content.add(wrap(homePage()), "Home");
        content.add(wrap(aboutPage()), "About");
        content.add(wrap(skillsPage()), "Skills");
        content.add(wrap(servicesPage()), "Services");
        content.add(wrap(projectsPage()), "Projects");
        content.add(wrap(contactPage()), "Contact");

        main.add(content, BorderLayout.CENTER);

        JLabel footer = new JLabel(
                "  © 2026  ROSIDHA K.     •     JAVA SWING PORTFOLIO"
        );

        footer.setOpaque(true);
        footer.setBackground(Color.WHITE);
        footer.setForeground(MUTED);
        footer.setFont(new Font("SansSerif", Font.PLAIN, 10));
        footer.setBorder(new EmptyBorder(11, 22, 11, 22));

        main.add(footer, BorderLayout.SOUTH);

        app.add(main, BorderLayout.CENTER);

        setContentPane(app);
        showPage("Home");
    }

    // SIDEBAR
    private JPanel createSidebar() {

        JPanel side = new JPanel();

        side.setPreferredSize(new Dimension(225, 0));
        side.setBackground(NAVY);
        side.setBorder(new EmptyBorder(24, 16, 20, 16));
        side.setLayout(new BoxLayout(side, BoxLayout.Y_AXIS));

        JLabel brand = new JLabel("RK.");
        brand.setForeground(Color.WHITE);
        brand.setFont(new Font("SansSerif", Font.BOLD, 31));
        brand.setAlignmentX(Component.LEFT_ALIGNMENT);

        side.add(brand);

        JLabel sub = new JLabel("PERSONAL PORTFOLIO");
        sub.setForeground(new Color(176, 194, 216));
        sub.setFont(new Font("SansSerif", Font.BOLD, 9));
        sub.setAlignmentX(Component.LEFT_ALIGNMENT);

        side.add(Box.createVerticalStrut(4));
        side.add(sub);
        side.add(Box.createVerticalStrut(38));

        String[][] nav = {
            {"⌂", "Home"},
            {"◎", "About"},
            {"▤", "Skills"},
            {"◇", "Services"},
            {"▣", "Projects"},
            {"✉", "Contact"}
        };

        for (String[] item : nav) {

            JButton b = new JButton(item[0] + "    " + item[1]);

            b.setHorizontalAlignment(SwingConstants.LEFT);
            b.setFont(new Font("SansSerif", Font.BOLD, 13));
            b.setForeground(new Color(224, 233, 245));
            b.setBackground(NAVY);
            b.setFocusPainted(false);
            b.setBorder(new EmptyBorder(13, 12, 13, 10));
            b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));
            b.setAlignmentX(Component.LEFT_ALIGNMENT);
            b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            b.addActionListener(e -> showPage(item[1]));

            side.add(b);
            side.add(Box.createVerticalStrut(5));
        }

        side.add(Box.createVerticalGlue());

        JPanel availability = new JPanel();

        availability.setOpaque(true);
        availability.setBackground(new Color(59, 54, 112));
        availability.setLayout(new BoxLayout(availability, BoxLayout.Y_AXIS));
        availability.setBorder(new EmptyBorder(13, 12, 13, 12));

        JLabel dot = new JLabel("●  OPEN TO OPPORTUNITIES");
        dot.setForeground(new Color(126, 231, 190));
        dot.setFont(new Font("SansSerif", Font.BOLD, 9));

        JLabel small = new JLabel("Learning • Building • Growing");
        small.setForeground(new Color(201, 214, 231));
        small.setFont(new Font("SansSerif", Font.PLAIN, 10));

        availability.add(dot);
        availability.add(Box.createVerticalStrut(7));
        availability.add(small);

        availability.setAlignmentX(Component.LEFT_ALIGNMENT);
        side.add(availability);

        return side;
    }

    // TOP BAR
    private JPanel createTopbar() {

        JPanel top = new JPanel(new BorderLayout());

        top.setBackground(Color.WHITE);
        top.setBorder(new EmptyBorder(18, 28, 18, 28));

        pageTitle.setFont(new Font("SansSerif", Font.BOLD, 21));
        pageTitle.setForeground(INK);

        JLabel right = new JLabel("ROSIDHA K.   |   JAVA DEVELOPER");

        right.setForeground(MUTED);
        right.setFont(new Font("SansSerif", Font.BOLD, 10));

        top.add(pageTitle, BorderLayout.WEST);
        top.add(right, BorderLayout.EAST);

        return top;
    }

    private void showPage(String name) {

        pageTitle.setText(name.equals("Home") ? "Overview" : name);
        cardLayout.show(content, name);
    }

    private JScrollPane wrap(JPanel panel) {

        JScrollPane sp = new JScrollPane(panel);

        sp.setBorder(null);
        sp.getVerticalScrollBar().setUnitIncrement(18);
        sp.getViewport().setBackground(BG);

        return sp;
    }

    private JPanel page() {

        JPanel p = new JPanel();

        p.setBackground(BG);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBorder(new EmptyBorder(27, 30, 32, 30));

        return p;
    }

    private JLabel heading(String text, int size) {

        JLabel l = new JLabel(text);

        l.setFont(new Font("SansSerif", Font.BOLD, size));
        l.setForeground(INK);
        l.setAlignmentX(Component.LEFT_ALIGNMENT);

        return l;
    }

    private JLabel muted(String text) {

        JLabel l = new JLabel(
                "<html><div style='width:820px'>" + text + "</div></html>"
        );

        l.setFont(new Font("SansSerif", Font.PLAIN, 13));
        l.setForeground(MUTED);
        l.setAlignmentX(Component.LEFT_ALIGNMENT);

        return l;
    }

    private void gap(JPanel p, int h) {

        p.add(Box.createRigidArea(new Dimension(0, h)));
    }

    private SoftCard card() {

        return new SoftCard(ACCENTS[nextAccent++ % ACCENTS.length]);
    }

    private JButton primaryButton(String text) {

        JButton b = new JButton(text);

        b.setBackground(BLUE);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("SansSerif", Font.BOLD, 12));
        b.setFocusPainted(false);
        b.setBorder(new EmptyBorder(12, 18, 12, 18));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        return b;
    }

    // HOME PAGE
    private JPanel homePage() {

        JPanel p = page();

        SoftCard hero = card();

        hero.setBackground(new Color(238, 235, 255));
        hero.setLayout(new BorderLayout(22, 10));
        hero.setBorder(new EmptyBorder(30, 32, 30, 32));
        hero.setMaximumSize(new Dimension(Integer.MAX_VALUE, 255));

        JPanel intro = new JPanel();

        intro.setOpaque(false);
        intro.setLayout(new BoxLayout(intro, BoxLayout.Y_AXIS));

        JLabel eyebrow = new JLabel("✦  HELLO, WELCOME TO MY PORTFOLIO");
        eyebrow.setForeground(TEAL);
        eyebrow.setFont(new Font("SansSerif", Font.BOLD, 10));

        JLabel name = new JLabel("I'm Rosidha K.");
        name.setForeground(NAVY);
        name.setFont(new Font("SansSerif", Font.BOLD, 37));

        JLabel role = new JLabel(
                "Aspiring Java Developer & Tech Enthusiast"
        );

        role.setForeground(BLUE);
        role.setFont(new Font("SansSerif", Font.BOLD, 16));

        JLabel desc = new JLabel(
                "<html><div style='width:580px'>"
                + "I enjoy learning programming, solving problems "
                + "and building useful applications with clean, "
                + "simple interfaces.</div></html>"
        );

        desc.setForeground(MUTED);
        desc.setFont(new Font("SansSerif", Font.PLAIN, 13));

        intro.add(eyebrow);
        gap(intro, 9);
        intro.add(name);
        gap(intro, 5);
        intro.add(role);
        gap(intro, 13);
        intro.add(desc);
        gap(intro, 18);

        JButton cta = primaryButton("EXPLORE MY WORK  →");

        cta.setAlignmentX(Component.LEFT_ALIGNMENT);
        cta.addActionListener(e -> showPage("Projects"));

        intro.add(cta);

        JLabel avatar = new JLabel("RK", SwingConstants.CENTER);

        avatar.setOpaque(true);
        avatar.setBackground(new Color(220, 215, 255));
        avatar.setForeground(new Color(91, 75, 190));
        avatar.setFont(new Font("SansSerif", Font.BOLD, 42));
        avatar.setPreferredSize(new Dimension(135, 135));
        avatar.setBorder(
                BorderFactory.createLineBorder(
                        new Color(190, 211, 239), 2
                )
        );

        hero.add(intro, BorderLayout.CENTER);
        hero.add(avatar, BorderLayout.EAST);

        p.add(hero);
        gap(p, 23);

        p.add(heading("Portfolio Snapshot", 20));
        gap(p, 12);

        JPanel stats = new JPanel(new GridLayout(1, 3, 14, 0));
        stats.setOpaque(false);

        stats.add(stat("06", "PORTFOLIO SECTIONS", "Explore my profile"));
        stats.add(stat("05", "CORE SKILL AREAS", "Learning and practising"));
        stats.add(stat("01", "CURRENT FOCUS", "Java development"));

        p.add(stats);
        gap(p, 23);

        p.add(heading("What I’m focused on", 19));
        gap(p, 10);

        SoftCard focus = card();

        focus.add(heading("Learning by building", 16));
        gap(focus, 7);

        focus.add(muted(
                "My current interests include Java programming, "
                + "object-oriented concepts, data structures, "
                + "desktop application development and creating "
                + "user-friendly experiences."
        ));

        p.add(focus);

        return p;
    }

    private JPanel stat(String number, String label, String note) {

        SoftCard c = card();

        JLabel n = new JLabel(number);

        n.setForeground(TEAL);
        n.setFont(new Font("SansSerif", Font.BOLD, 27));

        c.add(n);
        gap(c, 4);
        c.add(heading(label, 10));
        gap(c, 5);
        c.add(muted(note));

        return c;
    }

    // ABOUT PAGE
    private JPanel aboutPage() {

        JPanel p = page();

        p.add(heading("About Me", 28));
        gap(p, 7);

        p.add(muted(
                "A little about my interests, approach and goals "
                + "as an aspiring developer."
        ));

        gap(p, 18);

        SoftCard c = card();

        c.add(heading("Who I Am", 18));
        gap(c, 10);

        c.add(muted(
                "I am Rosidha K., an Information Technology student "
                + "interested in software development and problem solving. "
                + "I enjoy exploring how applications work, practising "
                + "programming concepts and turning ideas into useful projects."
        ));

        gap(c, 18);

        c.add(heading("My Approach", 16));
        gap(c, 8);

        c.add(muted(
                "Understand the requirement → Plan the solution → "
                + "Write readable code → Test the result → Improve continuously."
        ));

        p.add(c);
        gap(p, 14);

        SoftCard details = card();

        details.add(heading("Quick Profile", 17));
        gap(details, 10);

        details.add(detail("NAME", "Rosidha K."));
        details.add(detail("FIELD", "Information Technology"));
        details.add(detail(
                "INTERESTS",
                "Java, UI design, data structures and problem solving"
        ));

        details.add(detail(
                "CAREER GOAL",
                "Grow into a skilled software developer"
        ));

        p.add(details);

        return p;
    }

    private JLabel detail(String k, String v) {

        JLabel l = new JLabel(
                "<html><b style='color:#229D8B'>"
                + k + "</b>&nbsp;&nbsp;&nbsp; " + v + "</html>"
        );

        l.setFont(new Font("SansSerif", Font.PLAIN, 12));
        l.setForeground(INK);
        l.setBorder(new EmptyBorder(5, 0, 5, 0));

        return l;
    }

    // SKILLS PAGE
    private JPanel skillsPage() {

        JPanel p = page();

        p.add(heading("Skills & Technologies", 28));
        gap(p, 7);

        p.add(muted(
                "A sample skills overview. Please adjust the levels "
                + "to match your actual experience."
        ));

        gap(p, 18);

        String[][] skills = {
            {"Java Programming", "70",
                "Syntax, methods, arrays and problem solving"},
            {"Object-Oriented Programming", "65",
                "Classes, objects, inheritance and polymorphism"},
            {"Java Swing", "55",
                "Frames, panels, layouts, buttons and forms"},
            {"Data Structures", "50",
                "Arrays, linked lists, stacks and queues"},
            {"HTML & CSS Basics", "45",
                "Web page structure and styling fundamentals"}
        };

        for (String[] s : skills) {

            SoftCard c = card();

            JPanel row = new JPanel(new BorderLayout());
            row.setOpaque(false);

            row.add(heading(s[0], 14), BorderLayout.WEST);

            JLabel pct = new JLabel(s[1] + "%");

            pct.setForeground(TEAL);
            pct.setFont(new Font("SansSerif", Font.BOLD, 12));

            row.add(pct, BorderLayout.EAST);

            c.add(row);
            gap(c, 9);

            JProgressBar bar = new JProgressBar(0, 100);

            bar.setValue(Integer.parseInt(s[1]));
            bar.setForeground(new Color(0, 157, 143));
            bar.setBackground(new Color(231, 237, 245));
            bar.setBorderPainted(false);
            bar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 9));
            bar.setPreferredSize(new Dimension(500, 9));

            c.add(bar);
            gap(c, 7);
            c.add(muted(s[2]));

            p.add(c);
            gap(p, 11);
        }

        return p;
    }

    // SERVICES PAGE
    private JPanel servicesPage() {

        JPanel p = page();

        p.add(heading("Services & Interests", 28));
        gap(p, 7);

        p.add(muted(
                "Areas where I am developing skills and would like to contribute."
        ));

        gap(p, 18);

        String[][] services = {
            {"01", "Java Desktop Applications",
                "Creating beginner-friendly desktop tools using "
                + "Java Swing components and layouts."},

            {"02", "UI Design & Prototyping",
                "Organising screens and information into clear, "
                + "consistent user interfaces."},

            {"03", "Programming Practice",
                "Working through logic problems and improving "
                + "code readability and structure."},

            {"04", "Project Development",
                "Building small academic and personal projects "
                + "to apply programming concepts."}
        };

        for (String[] s : services) {

            SoftCard c = card();

            JLabel num = new JLabel(s[0]);

            num.setForeground(TEAL);
            num.setFont(new Font("SansSerif", Font.BOLD, 13));

            c.add(num);
            gap(c, 5);
            c.add(heading(s[1], 16));
            gap(c, 7);
            c.add(muted(s[2]));

            p.add(c);
            gap(p, 11);
        }

        return p;
    }

    // PROJECTS PAGE
    private JPanel projectsPage() {

        JPanel p = page();

        p.add(heading("Featured Projects", 28));
        gap(p, 7);

        p.add(muted(
                "Replace or extend these examples with your own completed "
                + "projects and GitHub repositories."
        ));

        gap(p, 18);

        String[][] projects = {
            {"01", "Java Freelancer Portfolio", "JAVA • SWING",
                "A desktop portfolio with profile, skills, services, "
                + "projects and a backend-connected contact form."},

            {"02", "Automated Bus Tracking System",
                "IOT • JAVA / EMBEDDED CONCEPT",
                "An academic project concept for tracking college buses "
                + "and sharing arrival or departure updates."},

            {"03", "Student Registration System",
                "JAVA • DATABASE CONCEPT",
                "A student information management concept with "
                + "registration fields and organised records."}
        };

        for (String[] s : projects) {

            SoftCard c = card();

            JPanel top = new JPanel(new BorderLayout());
            top.setOpaque(false);

            JLabel id = new JLabel(s[0]);

            id.setForeground(TEAL);
            id.setFont(new Font("SansSerif", Font.BOLD, 24));

            JLabel tech = new JLabel(s[2]);

            tech.setForeground(MUTED);
            tech.setFont(new Font("SansSerif", Font.BOLD, 9));

            top.add(id, BorderLayout.WEST);
            top.add(tech, BorderLayout.EAST);

            c.add(top);
            gap(c, 8);
            c.add(heading(s[1], 17));
            gap(c, 7);
            c.add(muted(s[3]));

            p.add(c);
            gap(p, 12);
        }

        return p;
    }

    // CONTACT PAGE
    private JPanel contactPage() {

        JPanel p = page();

        p.add(heading("Get In Touch", 28));
        gap(p, 7);

        p.add(muted(
                "Use this form to leave a message or share a project idea."
        ));

        gap(p, 18);

        JPanel cols = new JPanel(new GridLayout(1, 2, 16, 0));
        cols.setOpaque(false);

        SoftCard info = card();

        info.add(heading("Contact Information", 17));
        gap(info, 12);

        info.add(detail("NAME", "Rosidha K."));
        info.add(detail("EMAIL", "Add your email address"));
        info.add(detail("LOCATION", "Tamil Nadu, India"));

        gap(info, 15);

        info.add(muted(
                "You can add your GitHub, LinkedIn and email details "
                + "here before publishing your portfolio."
        ));

        cols.add(info);

        SoftCard form = card();

        form.add(heading("Send a Message", 17));
        gap(form, 11);

        JTextField name = new JTextField();
        JTextField email = new JTextField();
        JTextField subject = new JTextField();

        JTextArea message = new JTextArea(5, 20);

        message.setLineWrap(true);
        message.setWrapStyleWord(true);

        style(name);
        style(email);
        style(subject);
        style(message);

        addField(form, "YOUR NAME", name);
        addField(form, "EMAIL ADDRESS", email);
        addField(form, "SUBJECT", subject);
        addField(form, "MESSAGE", new JScrollPane(message));

        JButton send = primaryButton("SEND MESSAGE  →");

        send.setAlignmentX(Component.LEFT_ALIGNMENT);

        send.addActionListener(e ->
                saveMessage(name, email, subject, message, send)
        );

        form.add(send);

        cols.add(form);

        p.add(cols);
        gap(p, 12);

        p.add(muted(
                "Privacy: contact messages are submitted to the "
                + "Spring Boot backend and stored in the MySQL database."
        ));

        return p;
    }

    // TEXT FIELD STYLING
    private void style(JTextField f) {

        f.setFont(new Font("SansSerif", Font.PLAIN, 12));

        f.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 227, 237)
                        ),
                        new EmptyBorder(8, 9, 8, 9)
                )
        );

        f.setPreferredSize(new Dimension(300, 36));
        f.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
    }

    private void style(JTextArea a) {

        a.setFont(new Font("SansSerif", Font.PLAIN, 12));
        a.setBorder(new EmptyBorder(8, 9, 8, 9));
    }

    // ADD FORM FIELD
    private void addField(
            JPanel p,
            String label,
            JComponent field) {

        JLabel l = new JLabel(label);

        l.setForeground(MUTED);
        l.setFont(new Font("SansSerif", Font.BOLD, 9));
        l.setAlignmentX(Component.LEFT_ALIGNMENT);

        p.add(l);
        gap(p, 5);

        if (field instanceof JScrollPane) {

            field.setPreferredSize(new Dimension(300, 112));
            field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 112));
        }

        field.setAlignmentX(Component.LEFT_ALIGNMENT);

        p.add(field);
        gap(p, 10);
    }

    // SEND CONTACT MESSAGE TO SPRING BOOT
    private void saveMessage(
            JTextField name,
            JTextField email,
            JTextField subject,
            JTextArea message,
            JButton sendButton) {

        String n = name.getText().trim();
        String em = email.getText().trim();
        String sub = subject.getText().trim();
        String msg = message.getText().trim();

        // Check empty fields
        if (n.isEmpty() || em.isEmpty()
                || sub.isEmpty() || msg.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all fields.",
                    "Missing information",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Check email format
        if (!em.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid email address.",
                    "Invalid email",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Convert contact details into JSON
        String json = "{"
                + "\"name\":\"" + escapeJson(n) + "\","
                + "\"email\":\"" + escapeJson(em) + "\","
                + "\"subject\":\"" + escapeJson(sub) + "\","
                + "\"message\":\"" + escapeJson(msg) + "\""
                + "}";

        // Create HTTP POST request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .POST(
                        HttpRequest.BodyPublishers.ofString(
                                json,
                                StandardCharsets.UTF_8
                        )
                )
                .build();

        // Prevent multiple submissions
        sendButton.setEnabled(false);
        sendButton.setText("SENDING...");

        // Send request in the background
        SwingWorker<HttpResponse<String>, Void> worker =
                new SwingWorker<>() {

            @Override
            protected HttpResponse<String> doInBackground()
                    throws Exception {

                return httpClient.send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                );
            }

            @Override
            protected void done() {

                sendButton.setEnabled(true);
                sendButton.setText("SEND MESSAGE  →");

                try {

                    HttpResponse<String> response = get();

                    if (response.statusCode() == 201) {

                        JOptionPane.showMessageDialog(
                                PortfolioApp.this,
                                "Your message was saved successfully!",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                        name.setText("");
                        email.setText("");
                        subject.setText("");
                        message.setText("");

                    } else {

                        JOptionPane.showMessageDialog(
                                PortfolioApp.this,
                                "Backend returned status: "
                                + response.statusCode()
                                + "\nPlease check your backend.",
                                "Submission failed",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(
                            PortfolioApp.this,
                            "Unable to connect to the backend.\n"
                            + "Please make sure Spring Boot is running "
                            + "on port 8080.\n\n"
                            + "Error: " + ex.getMessage(),
                            "Connection error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        };

        worker.execute();
    }

    // ESCAPE SPECIAL CHARACTERS FOR JSON
    private String escapeJson(String value) {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < value.length(); i++) {

            char c = value.charAt(i);

            switch (c) {

                case '"':
                    result.append("\\\"");
                    break;

                case '\\':
                    result.append("\\\\");
                    break;

                case '\b':
                    result.append("\\b");
                    break;

                case '\f':
                    result.append("\\f");
                    break;

                case '\n':
                    result.append("\\n");
                    break;

                case '\r':
                    result.append("\\r");
                    break;

                case '\t':
                    result.append("\\t");
                    break;

                default:
                    if (c < 0x20) {
                        result.append(String.format("\\u%04x", (int) c));
                    } else {
                        result.append(c);
                    }
            }
        }

        return result.toString();
    }

    // ROUNDED CARD DESIGN
    private static class SoftCard extends JPanel {

        private final Color accent;

        SoftCard(Color accent) {

            this.accent = accent;

            setBackground(Color.WHITE);
            setOpaque(false);

            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            setBorder(new EmptyBorder(21, 20, 18, 20));
        }

        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(getBackground());

            g2.fillRoundRect(
                    0, 0, getWidth(), getHeight(), 18, 18
            );

            g2.setColor(new Color(232, 234, 245));

            g2.drawRoundRect(
                    0, 0, getWidth() - 1, getHeight() - 1, 18, 18
            );

            g2.setColor(accent);

            g2.fillRoundRect(
                    18, 0, Math.max(35, getWidth() - 36), 5, 5, 5
            );

            g2.dispose();

            super.paintComponent(g);
        }
    }

    // MAIN METHOD
    public static void main(String[] args) {

        try {

            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
            );

        } catch (Exception ignored) {
        }

        SwingUtilities.invokeLater(
                () -> new PortfolioApp().setVisible(true)
        );
    }
}
