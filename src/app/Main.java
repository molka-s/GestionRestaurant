package app;

import model.Client;
import model.Article;
import model.Commande;
import service.ClientService;
import service.ArticleService;
import service.CommandeService;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.border.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main {
    public static Client clientConnecte = null;

    private static ClientService clientService = new ClientService();
    private static ArticleService articleService = new ArticleService();
    private static CommandeService commandeService = new CommandeService(articleService);

    // chemins des images
    private static final String IMAGE_PATH = "resources/images/";
    private static final String LOGO_PATH = IMAGE_PATH + "logo.png";
    private static final String BACKGROUND_PATH = IMAGE_PATH + "home.jpg";
    private static final String MENU_BACKGROUND_PATH = IMAGE_PATH + "bg_card.jpg";
    private static final String STATS_ICON_PATH = IMAGE_PATH + "stats_icon.png";
    private static final String SALES_ICON_PATH = IMAGE_PATH + "sales_icon.png";
    private static final String CLIENTS_ICON_PATH = IMAGE_PATH + "clients_icon.png";
    private static final String POPULAR_ICON_PATH = IMAGE_PATH + "popular_icon.png";
    
    // couleurs 
    private static final Color PRIMARY_COLOR = new Color(0, 102, 204);
    private static final Color SECONDARY_COLOR = new Color(240, 240, 240);
    private static final Color ACCENT_COLOR = new Color(220, 53, 69);
    private static final Color DARK_COLOR = new Color(33, 37, 41);
    private static final Color LIGHT_COLOR = new Color(248, 249, 250);
    private static final Color WHITE = Color.WHITE;
    private static final Color MENU_BG_COLOR = new Color(250, 245, 240);
    private static final Color MENU_HEADER_COLOR = new Color(120, 50, 30);
    private static final Color MENU_ITEM_COLOR = new Color(70, 70, 70);
    private static final Color GOLD_COLOR = new Color(255, 215, 0);
    
    // icones
    private static ImageIcon logoIcon;
    private static ImageIcon homeIcon;
    private static ImageIcon clientIcon;
    private static ImageIcon articleIcon;
    private static ImageIcon orderIcon;
    private static ImageIcon loginIcon;
    private static ImageIcon logoutIcon;
    private static ImageIcon addIcon;
    private static ImageIcon editIcon;
    private static ImageIcon deleteIcon;
    private static ImageIcon refreshIcon;
    private static ImageIcon detailIcon;
    private static ImageIcon statusIcon;
    private static ImageIcon menuIcon;
    private static ImageIcon cartIcon;
    private static ImageIcon userIcon;
    private static ImageIcon statsIcon;
    private static ImageIcon imageIcon;
    private static ImageIcon chartIcon;
    private static ImageIcon salesIcon;
    private static ImageIcon loyaltyIcon;
    private static ImageIcon statsGeneralIcon;
    private static ImageIcon statsSalesIcon;
    private static ImageIcon statsClientsIcon;
    private static ImageIcon statsPopularIcon;

    private static JFrame frame;

    public static void main(String[] args) {
        initializeIcons();
        
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }
    
    private static void initializeIcons() {
        try {
            logoIcon = loadImage(LOGO_PATH, 128, 128);
            homeIcon = loadImage(IMAGE_PATH + "home.png", 24, 24);
            clientIcon = loadImage(IMAGE_PATH + "client.png", 24, 24);
            articleIcon = loadImage(IMAGE_PATH + "article.png", 24, 24);
            orderIcon = loadImage(IMAGE_PATH + "order.png", 24, 24);
            loginIcon = loadImage(IMAGE_PATH + "login.png", 24, 24);
            logoutIcon = loadImage(IMAGE_PATH + "logout.png", 24, 24);
            addIcon = loadImage(IMAGE_PATH + "add.png", 24, 24);
            editIcon = loadImage(IMAGE_PATH + "edit.png", 24, 24);
            deleteIcon = loadImage(IMAGE_PATH + "delete.png", 24, 24);
            refreshIcon = loadImage(IMAGE_PATH + "refresh.png", 24, 24);
            detailIcon = loadImage(IMAGE_PATH + "detail.png", 24, 24);
            statusIcon = loadImage(IMAGE_PATH + "status.png", 24, 24);
            menuIcon = loadImage(IMAGE_PATH + "menu.png", 24, 24);
            cartIcon = loadImage(IMAGE_PATH + "cart.png", 24, 24);
            userIcon = loadImage(IMAGE_PATH + "user.png", 24, 24);
            statsIcon = loadImage(IMAGE_PATH + "stats.png", 24, 24);
            imageIcon = loadImage(IMAGE_PATH + "image.png", 24, 24);
            chartIcon = loadImage(IMAGE_PATH + "chart.png", 24, 24);
            salesIcon = loadImage(IMAGE_PATH + "sales.png", 24, 24);
            loyaltyIcon = loadImage(IMAGE_PATH + "loyalty.png", 24, 24);
            
            statsGeneralIcon = loadImage(STATS_ICON_PATH, 64, 64);
            statsSalesIcon = loadImage(SALES_ICON_PATH, 64, 64);
            statsClientsIcon = loadImage(CLIENTS_ICON_PATH, 64, 64);
            statsPopularIcon = loadImage(POPULAR_ICON_PATH, 64, 64);
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement des images: " + e.getMessage());
            createDefaultIcons();
        }
    }
    
    private static void createDefaultIcons() {
        logoIcon = createDefaultIcon(128, 128, "LOGO");
        homeIcon = createDefaultIcon(24, 24, "HOME");
        clientIcon = createDefaultIcon(24, 24, "CLI");
        articleIcon = createDefaultIcon(24, 24, "ART");
        orderIcon = createDefaultIcon(24, 24, "ORD");
        loginIcon = createDefaultIcon(24, 24, "LOG");
        logoutIcon = createDefaultIcon(24, 24, "OUT");
        addIcon = createDefaultIcon(24, 24, "ADD");
        editIcon = createDefaultIcon(24, 24, "EDT");
        deleteIcon = createDefaultIcon(24, 24, "DEL");
        refreshIcon = createDefaultIcon(24, 24, "REF");
        detailIcon = createDefaultIcon(24, 24, "DET");
        statusIcon = createDefaultIcon(24, 24, "STA");
        menuIcon = createDefaultIcon(24, 24, "MEN");
        cartIcon = createDefaultIcon(24, 24, "CRT");
        userIcon = createDefaultIcon(24, 24, "USR");
        statsIcon = createDefaultIcon(24, 24, "STT");
        imageIcon = createDefaultIcon(24, 24, "IMG");
        chartIcon = createDefaultIcon(24, 24, "CHT");
        salesIcon = createDefaultIcon(24, 24, "SAL");
        loyaltyIcon = createDefaultIcon(24, 24, "LOY");
        
        statsGeneralIcon = createDefaultIcon(64, 64, "GEN");
        statsSalesIcon = createDefaultIcon(64, 64, "SAL");
        statsClientsIcon = createDefaultIcon(64, 64, "CLI");
        statsPopularIcon = createDefaultIcon(64, 64, "POP");
    }
    
    private static ImageIcon createDefaultIcon(int width, int height, String text) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        
        g2d.setComposite(AlphaComposite.Clear);
        g2d.fillRect(0, 0, width, height);
        g2d.setComposite(AlphaComposite.Src);
        
        g2d.setColor(PRIMARY_COLOR);
        g2d.fillOval(2, 2, width-4, height-4);
        
        g2d.setColor(WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, width > 32 ? 16 : 10));
        FontMetrics fm = g2d.getFontMetrics();
        int x = (width - fm.stringWidth(text)) / 2;
        int y = ((height - fm.getHeight()) / 2) + fm.getAscent();
        g2d.drawString(text, x, y);
        
        g2d.dispose();
        return new ImageIcon(image);
    }
    
    private static ImageIcon loadImage(String path, int width, int height) throws Exception {
        File file = new File(path);
        if (!file.exists()) {
            throw new Exception("Fichier image non trouvé: " + path);
        }
        Image image = ImageIO.read(file);
        return new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }

    private static void createAndShowGUI() {
        frame = new JFrame("Gestion Restaurant FoodiGo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 800);
        frame.setLocationRelativeTo(null);

        // charger la police 
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, Main.class.getResourceAsStream("/fonts/Poppins-Regular.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement de la police: " + e.getMessage());
        }

        JPanel mainPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    File bgFile = new File(BACKGROUND_PATH);
                    if (bgFile.exists()) {
                        Image bgImage = ImageIO.read(bgFile);
                        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
                    } else {
                        GradientPaint gradient = new GradientPaint(0, 0, new Color(230, 240, 255), 
                                      getWidth(), getHeight(), new Color(180, 200, 255));
                        ((Graphics2D)g).setPaint(gradient);
                        g.fillRect(0, 0, getWidth(), getHeight());
                    }
                } catch (Exception e) {
                    GradientPaint gradient = new GradientPaint(0, 0, new Color(230, 240, 255), 
                                      getWidth(), getHeight(), new Color(180, 200, 255));
                    ((Graphics2D)g).setPaint(gradient);
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        mainPanel.setOpaque(false);
        mainPanel.setBorder(new EmptyBorder(0, 0, 0, 0));

        JPanel contentPanel = new JPanel(new CardLayout());
        contentPanel.setOpaque(false);
        contentPanel.setBorder(new EmptyBorder(0, 0, 0, 0));

        JPanel homePanel = createHomePanel(contentPanel);
        JPanel loginGerantPanel = createLoginGerantPanel(contentPanel);
        JPanel menuGerantPanel = createMenuGerantPanel(contentPanel);
        JPanel gestionClientsPanel = createGestionClientsPanel(contentPanel);
        JPanel gestionArticlesPanel = createGestionArticlesPanel(contentPanel);
        JPanel gestionCommandesPanel = createGestionCommandesPanel(contentPanel);
        JPanel loginClientPanel = createLoginClientPanel(contentPanel);
        JPanel menuClientPanel = createMenuClientPanel(contentPanel);
        JPanel statistiquesPanel = createStatisticsPanel(contentPanel);

        contentPanel.add(homePanel, "HOME");
        contentPanel.add(loginGerantPanel, "LOGIN_GERANT");
        contentPanel.add(menuGerantPanel, "MENU_GERANT");
        contentPanel.add(gestionClientsPanel, "GESTION_CLIENTS");
        contentPanel.add(gestionArticlesPanel, "GESTION_ARTICLES");
        contentPanel.add(gestionCommandesPanel, "GESTION_COMMANDES");
        contentPanel.add(loginClientPanel, "LOGIN_CLIENT");
        contentPanel.add(menuClientPanel, "MENU_CLIENT");
        contentPanel.add(statistiquesPanel, "STATISTIQUES");

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }

    private static JPanel createHomePanel(JPanel contentPanel) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        // barre de menu 
        JPanel topMenu = new JPanel(new BorderLayout());
        topMenu.setOpaque(false);
        topMenu.setBorder(new EmptyBorder(20, 20, 20, 20));

        // logo 
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBorder(new EmptyBorder(10, 20, 10, 0));
        topMenu.add(logoLabel, BorderLayout.WEST);

        // panel pour les boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 20));
        buttonPanel.setOpaque(false);

        JButton gerantButton = createStyledButton("     Espace Gérant", PRIMARY_COLOR, loginIcon);
        gerantButton.setFont(new Font("Poppins", Font.BOLD, 16));
        gerantButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) contentPanel.getLayout();
            cl.show(contentPanel, "LOGIN_GERANT");
        });

        JButton clientButton = createStyledButton("      Espace Client", PRIMARY_COLOR, loginIcon);
        clientButton.setFont(new Font("Poppins", Font.BOLD, 16));
        clientButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) contentPanel.getLayout();
            cl.show(contentPanel, "LOGIN_CLIENT");
        });

        buttonPanel.add(gerantButton);
        buttonPanel.add(clientButton);
        topMenu.add(buttonPanel, BorderLayout.EAST);
        panel.add(topMenu, BorderLayout.NORTH);

        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setOpaque(false);

        JPanel centerContent = new JPanel();
        centerContent.setLayout(new BoxLayout(centerContent, BoxLayout.Y_AXIS));
        centerContent.setOpaque(false);
        centerContent.setBorder(new EmptyBorder(50, 0, 0, 0));

        JLabel titleLabel = new JLabel("Bienvenue à FoodiGo", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 48));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel sloganLabel = new JLabel("Commandez, partez, savourez...", SwingConstants.CENTER);
        sloganLabel.setFont(new Font("Poppins", Font.ITALIC, 22));
        sloganLabel.setForeground(Color.LIGHT_GRAY);
        sloganLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sloganLabel.setBorder(new EmptyBorder(20, 0, 30, 0));

        // description 
        JPanel descPanel = new JPanel();
        descPanel.setLayout(new BoxLayout(descPanel, BoxLayout.Y_AXIS));
        descPanel.setOpaque(false);
        descPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        descPanel.setMaximumSize(new Dimension(800, 200));

        JLabel descLabel = new JLabel(
            "<html><div style='text-align: center; color: #EEEEEE; font-size: 16px;'>"
            + "Notre plateforme offre une expérience fluide, adaptée aux besoins des clients et des gérants.<br><br>"
            + "Conçue comme un espace centralisé de gestion, elle permet aux gérants de suivre les activités, gérer les utilisateurs et optimiser leurs opérations, "
            + "tandis que les clients peuvent accéder facilement à leurs services, suivre leur historique et interagir en toute simplicité."
            + "</div></html>",
            SwingConstants.CENTER
        );
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        descLabel.setBorder(new EmptyBorder(0, 50, 0, 50)); 
        descPanel.add(descLabel);

        centerContent.add(titleLabel);
        centerContent.add(sloganLabel);
        centerContent.add(descPanel);
        
        centerWrapper.add(centerContent);
        panel.add(centerWrapper, BorderLayout.CENTER);

        return panel;
    }

    private static JPanel createLoginGerantPanel(JPanel contentPanel) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(LIGHT_COLOR);
        panel.setBorder(new EmptyBorder(50, 50, 50, 50));

        // panel principal
        JPanel cardPanel = new JPanel(new GridBagLayout());
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(PRIMARY_COLOR, 2),
            new EmptyBorder(40, 40, 40, 40)
        ));

        JLabel iconLabel = new JLabel(loginIcon);
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabel.setBorder(new EmptyBorder(0, 0, 20, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        cardPanel.add(iconLabel, gbc);

        // titre 
        JLabel titleLabel = new JLabel("CONNEXION GÉRANT");
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 24));
        titleLabel.setForeground(PRIMARY_COLOR);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(new EmptyBorder(0, 0, 30, 0));
        gbc.gridy++;
        cardPanel.add(titleLabel, gbc);

        // formulaire
        gbc.gridwidth = 1;
        gbc.gridy++;
        JLabel loginLabel = new JLabel("Login:");
        loginLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        cardPanel.add(loginLabel, gbc);

        gbc.gridx = 1;
        JTextField loginField = new JTextField(20);
        loginField.setFont(new Font("Poppins", Font.PLAIN, 16));
        loginField.setPreferredSize(new Dimension(300, 40));
        loginField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        cardPanel.add(loginField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel passwordLabel = new JLabel("Mot de passe:");
        passwordLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        cardPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Poppins", Font.PLAIN, 16));
        passwordField.setPreferredSize(new Dimension(300, 40));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        cardPanel.add(passwordField, gbc);

        // boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(new EmptyBorder(30, 0, 0, 0));

        JButton loginButton = createStyledButton("Se connecter", PRIMARY_COLOR, loginIcon);
        loginButton.setFont(new Font("Poppins", Font.BOLD, 16));
        loginButton.setPreferredSize(new Dimension(200, 45));
        loginButton.addActionListener(e -> {
            String login = loginField.getText();
            String password = new String(passwordField.getPassword());
            
            if ("admin".equals(login) && "admin123".equals(password)) {
                CardLayout cl = (CardLayout)(contentPanel.getLayout());
                cl.show(contentPanel, "MENU_GERANT");
            } else {
                JOptionPane.showMessageDialog(frame, "Identifiants incorrects!", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton backButton = createStyledButton("Retour", DARK_COLOR, homeIcon);
        backButton.setFont(new Font("Poppins", Font.BOLD, 16));
        backButton.setPreferredSize(new Dimension(200, 45));
        backButton.addActionListener(e -> {
            CardLayout cl = (CardLayout)(contentPanel.getLayout());
            cl.show(contentPanel, "HOME");
        });

        buttonPanel.add(loginButton);
        buttonPanel.add(backButton);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        cardPanel.add(buttonPanel, gbc);

        panel.add(cardPanel, BorderLayout.CENTER);

        return panel;
    }

    private static JPanel createMenuGerantPanel(JPanel contentPanel) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(LIGHT_COLOR);
        panel.setBorder(new EmptyBorder(30, 50, 50, 50));

        JPanel welcomePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        welcomePanel.setBackground(LIGHT_COLOR);

        JLabel welcomeLabel = new JLabel("Bienvenue dans l'espace Gérant");
        welcomeLabel.setFont(new Font("Poppins", Font.BOLD, 24));
        welcomeLabel.setForeground(PRIMARY_COLOR);
        welcomeLabel.setBorder(new EmptyBorder(0, 0, 30, 0));

        welcomePanel.add(welcomeLabel);

        JPanel cardPanel = new JPanel(new GridBagLayout());
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(PRIMARY_COLOR, 2),
            new EmptyBorder(40, 40, 40, 40)
        ));

        // boutons menu
        JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 0, 25)); 
        buttonPanel.setBackground(Color.WHITE);

        JButton clientsButton = createStyledButton("  Gestion des clients", PRIMARY_COLOR, clientIcon);
        clientsButton.setFont(new Font("Poppins", Font.BOLD, 18));
        clientsButton.setPreferredSize(new Dimension(300, 60));
        clientsButton.addActionListener(e -> {
            CardLayout cl = (CardLayout)(contentPanel.getLayout());
            cl.show(contentPanel, "GESTION_CLIENTS");
        });

        JButton articlesButton = createStyledButton("  Gestion des articles", PRIMARY_COLOR, articleIcon);
        articlesButton.setFont(new Font("Poppins", Font.BOLD, 18));
        articlesButton.setPreferredSize(new Dimension(300, 60));
        articlesButton.addActionListener(e -> {
            CardLayout cl = (CardLayout)(contentPanel.getLayout());
            cl.show(contentPanel, "GESTION_ARTICLES");
        });
        
        JButton consulterMenuButton = createStyledButton("  Menu", PRIMARY_COLOR, menuIcon);
        consulterMenuButton.setFont(new Font("Poppins", Font.BOLD, 18));
        consulterMenuButton.setPreferredSize(new Dimension(300, 60));
        consulterMenuButton.addActionListener(e -> {
            showMenuDialog(); 
        });

        JButton commandesButton = createStyledButton("  Gestion des commandes", PRIMARY_COLOR, orderIcon);
        commandesButton.setFont(new Font("Poppins", Font.BOLD, 18));
        commandesButton.setPreferredSize(new Dimension(300, 60));
        commandesButton.addActionListener(e -> {
            CardLayout cl = (CardLayout)(contentPanel.getLayout());
            cl.show(contentPanel, "GESTION_COMMANDES");
        });

        JButton statistiquesButton = createStyledButton("  Statistiques", PRIMARY_COLOR, statsIcon);
        statistiquesButton.setFont(new Font("Poppins", Font.BOLD, 18));
        statistiquesButton.setPreferredSize(new Dimension(300, 60));
        statistiquesButton.addActionListener(e -> {
            CardLayout cl = (CardLayout)(contentPanel.getLayout());
            cl.show(contentPanel, "STATISTIQUES");
        });



        JButton backButton = createStyledButton("  Déconnexion", DARK_COLOR, logoutIcon);
        backButton.setFont(new Font("Poppins", Font.BOLD, 18));
        backButton.setPreferredSize(new Dimension(300, 60));
        backButton.addActionListener(e -> {
            CardLayout cl = (CardLayout)(contentPanel.getLayout());
            cl.show(contentPanel, "HOME");
        });

        buttonPanel.add(clientsButton);
        buttonPanel.add(articlesButton);
        buttonPanel.add(consulterMenuButton); 
        buttonPanel.add(commandesButton);
        buttonPanel.add(statistiquesButton);
        buttonPanel.add(backButton);

        cardPanel.add(buttonPanel);
        panel.add(welcomePanel, BorderLayout.NORTH);
        panel.add(cardPanel, BorderLayout.CENTER);

        return panel;
    }


    private static JPanel createGestionClientsPanel(JPanel contentPanel) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(LIGHT_COLOR);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        //tableau des clients
        String[] columnNames = {"Login", "Nom", "Prénom", "Adresse", "Téléphone", "Date Naissance"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(model);
        table.setFont(new Font("Poppins", Font.PLAIN, 14));
        table.setRowHeight(35);
        table.setSelectionBackground(SECONDARY_COLOR);
        table.setSelectionForeground(DARK_COLOR);
        table.setGridColor(new Color(200, 200, 200));
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(PRIMARY_COLOR, 2), 
            "Liste des clients", 
            javax.swing.border.TitledBorder.LEFT, 
            javax.swing.border.TitledBorder.TOP,
            new Font("Poppins", Font.BOLD, 18),
            PRIMARY_COLOR));

        //boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        buttonPanel.setBackground(LIGHT_COLOR);
        
        JButton refreshButton = createStyledButton("  Actualiser", PRIMARY_COLOR, refreshIcon);
        refreshButton.setFont(new Font("Poppins", Font.BOLD, 16));
        JButton addButton = createStyledButton("  Ajouter", PRIMARY_COLOR, addIcon);
        addButton.setFont(new Font("Poppins", Font.BOLD, 16));
        JButton editButton = createStyledButton("  Modifier", PRIMARY_COLOR, editIcon);
        editButton.setFont(new Font("Poppins", Font.BOLD, 16));
        JButton deleteButton = createStyledButton("  Supprimer", ACCENT_COLOR, deleteIcon);
        deleteButton.setFont(new Font("Poppins", Font.BOLD, 16));
        JButton backButton = createStyledButton("  Retour", DARK_COLOR, homeIcon);
        backButton.setFont(new Font("Poppins", Font.BOLD, 16));

        refreshButton.addActionListener(e -> refreshClientTable(model));
        addButton.addActionListener(e -> showAddClientDialog(model));
        editButton.addActionListener(e -> showEditClientDialog());
        deleteButton.addActionListener(e -> askLoginAndDeleteClient(model));
        backButton.addActionListener(e -> {
            CardLayout cl = (CardLayout)(contentPanel.getLayout());
            cl.show(contentPanel, "MENU_GERANT");
        });

        buttonPanel.add(refreshButton);
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        refreshClientTable(model);

        return panel;
    }

    private static void refreshClientTable(DefaultTableModel model) {
        model.setRowCount(0);
        List<Client> clients = clientService.listerClients();
        for (Client client : clients) {
            model.addRow(new Object[]{
                client.getLogin(),
                client.getNom(),
                client.getPrenom(),
                client.getAdresse(),
                client.getTelephone(),
                client.getDateNaissance()
            });
        }
    }

    private static void showAddClientDialog(DefaultTableModel model) {
        JDialog dialog = new JDialog(frame, "Ajouter un client", true);
        dialog.setSize(600, 500);
        dialog.setLocationRelativeTo(frame);
        dialog.setLayout(new BorderLayout());
        dialog.getContentPane().setBackground(LIGHT_COLOR);

        JPanel formPanel = new JPanel(new GridLayout(7, 2, 15, 15));
        formPanel.setBackground(LIGHT_COLOR);
        formPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        JLabel loginLabel = new JLabel("Login:");
        loginLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        JTextField loginField = new JTextField();
        loginField.setFont(new Font("Poppins", Font.PLAIN, 16));
        
        JLabel passwordLabel = new JLabel("Mot de passe:");
        passwordLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Poppins", Font.PLAIN, 16));
        
        JLabel nomLabel = new JLabel("Nom:");
        nomLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        JTextField nomField = new JTextField();
        nomField.setFont(new Font("Poppins", Font.PLAIN, 16));
        
        JLabel prenomLabel = new JLabel("Prénom:");
        prenomLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        JTextField prenomField = new JTextField();
        prenomField.setFont(new Font("Poppins", Font.PLAIN, 16));
        
        JLabel dateLabel = new JLabel("Date naissance (AAAA-MM-JJ):");
        dateLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        JTextField dateField = new JTextField();
        dateField.setFont(new Font("Poppins", Font.PLAIN, 16));
        
        JLabel adresseLabel = new JLabel("Adresse:");
        adresseLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        JTextField adresseField = new JTextField();
        adresseField.setFont(new Font("Poppins", Font.PLAIN, 16));
        
        JLabel telLabel = new JLabel("Téléphone:");
        telLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        JTextField telField = new JTextField();
        telField.setFont(new Font("Poppins", Font.PLAIN, 16));

        formPanel.add(loginLabel);
        formPanel.add(loginField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(nomLabel);
        formPanel.add(nomField);
        formPanel.add(prenomLabel);
        formPanel.add(prenomField);
        formPanel.add(dateLabel);
        formPanel.add(dateField);
        formPanel.add(adresseLabel);
        formPanel.add(adresseField);
        formPanel.add(telLabel);
        formPanel.add(telField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        buttonPanel.setBackground(LIGHT_COLOR);
        JButton saveButton = createStyledButton("  Enregistrer", PRIMARY_COLOR, null);
        saveButton.setFont(new Font("Poppins", Font.BOLD, 16));
        JButton cancelButton = createStyledButton("  Annuler", DARK_COLOR, null);
        cancelButton.setFont(new Font("Poppins", Font.BOLD, 16));

        saveButton.addActionListener(e -> {
            try {
                Client client = new Client(
                    loginField.getText(),
                    new String(passwordField.getPassword()),
                    nomField.getText(),
                    prenomField.getText(),
                    LocalDate.parse(dateField.getText()),
                    adresseField.getText(),
                    telField.getText()
                );

                if (clientService.creerClient(client)) {
                    refreshClientTable(model);
                    dialog.dispose();
                    JOptionPane.showMessageDialog(frame, "Client ajouté avec succès");
                } else {
                    JOptionPane.showMessageDialog(frame, "Erreur lors de l'ajout du client", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Données invalides", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private static void showEditClientDialog() {
        JTextField loginInput = new JTextField();
        loginInput.setFont(new Font("Poppins", Font.PLAIN, 16));

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Saisir le login du client à modifier:"), BorderLayout.NORTH);
        panel.add(loginInput, BorderLayout.CENTER);

        int result = JOptionPane.showConfirmDialog(frame, panel, 
                "Modifier un client", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String login = loginInput.getText().trim();
            Client client = clientService.trouverClientParLogin(login);

            if (client == null) {
                JOptionPane.showMessageDialog(frame, "Aucun client trouvé avec ce login.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            showEditClientForm(client);
        }
    }
    
    private static void showEditClientForm(Client client) {
        JDialog dialog = new JDialog(frame, "Modifier un client", true);
        dialog.setSize(600, 500);
        dialog.setLocationRelativeTo(frame);
        dialog.setLayout(new BorderLayout());
        dialog.getContentPane().setBackground(LIGHT_COLOR);

        JPanel formPanel = new JPanel(new GridLayout(7, 2, 15, 15));
        formPanel.setBackground(LIGHT_COLOR);
        formPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        JTextField loginField = new JTextField(client.getLogin());
        loginField.setEditable(false);
        loginField.setFont(new Font("Poppins", Font.PLAIN, 16));
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Poppins", Font.PLAIN, 16));
        JTextField nomField = new JTextField(client.getNom());
        nomField.setFont(new Font("Poppins", Font.PLAIN, 16));
        JTextField prenomField = new JTextField(client.getPrenom());
        prenomField.setFont(new Font("Poppins", Font.PLAIN, 16));
        JTextField dateField = new JTextField(client.getDateNaissance().toString());
        dateField.setFont(new Font("Poppins", Font.PLAIN, 16));
        JTextField adresseField = new JTextField(client.getAdresse());
        adresseField.setFont(new Font("Poppins", Font.PLAIN, 16));
        JTextField telField = new JTextField(client.getTelephone());
        telField.setFont(new Font("Poppins", Font.PLAIN, 16));

        formPanel.add(new JLabel("Login:"));
        ((JLabel)formPanel.getComponent(0)).setFont(new Font("Poppins", Font.BOLD, 16));
        formPanel.add(loginField);
        formPanel.add(new JLabel("Nouveau mot de passe:"));
        ((JLabel)formPanel.getComponent(2)).setFont(new Font("Poppins", Font.BOLD, 16));
        formPanel.add(passwordField);
        formPanel.add(new JLabel("Nom:"));
        ((JLabel)formPanel.getComponent(4)).setFont(new Font("Poppins", Font.BOLD, 16));
        formPanel.add(nomField);
        formPanel.add(new JLabel("Prénom:"));
        ((JLabel)formPanel.getComponent(6)).setFont(new Font("Poppins", Font.BOLD, 16));
        formPanel.add(prenomField);
        formPanel.add(new JLabel("Date naissance (AAAA-MM-JJ):"));
        ((JLabel)formPanel.getComponent(8)).setFont(new Font("Poppins", Font.BOLD, 16));
        formPanel.add(dateField);
        formPanel.add(new JLabel("Adresse:"));
        ((JLabel)formPanel.getComponent(10)).setFont(new Font("Poppins", Font.BOLD, 16));
        formPanel.add(adresseField);
        formPanel.add(new JLabel("Téléphone:"));
        ((JLabel)formPanel.getComponent(12)).setFont(new Font("Poppins", Font.BOLD, 16));
        formPanel.add(telField);

        //boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        buttonPanel.setBackground(LIGHT_COLOR);
        JButton saveButton = createStyledButton("Enregistrer", PRIMARY_COLOR, null);
        saveButton.setFont(new Font("Poppins", Font.BOLD, 16));
        JButton cancelButton = createStyledButton("Annuler", DARK_COLOR, null);
        cancelButton.setFont(new Font("Poppins", Font.BOLD, 16));

        saveButton.addActionListener(e -> {
            try {
                String newPassword = new String(passwordField.getPassword());
                if (!newPassword.isEmpty()) client.setPassword(newPassword);

                client.setNom(nomField.getText());
                client.setPrenom(prenomField.getText());
                client.setDateNaissance(LocalDate.parse(dateField.getText()));
                client.setAdresse(adresseField.getText());
                client.setTelephone(telField.getText());

                if (clientService.modifierClient(client)) {
                    JOptionPane.showMessageDialog(frame, "Client modifié avec succès");
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Erreur lors de la modification", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Données invalides", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private static void askLoginAndDeleteClient(DefaultTableModel model) {
        JTextField loginInput = new JTextField();
        loginInput.setFont(new Font("Poppins", Font.PLAIN, 16));

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Saisir le login du client à supprimer:"), BorderLayout.NORTH);
        panel.add(loginInput, BorderLayout.CENTER);

        int result = JOptionPane.showConfirmDialog(frame, panel,
                "Supprimer un client", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String login = loginInput.getText().trim();
            if (login.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Le login ne peut pas être vide", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Client client = clientService.trouverClientParLogin(login);
            if (client == null) {
                JOptionPane.showMessageDialog(frame, "Aucun client trouvé avec ce login", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(frame,
                    "Êtes-vous sûr de vouloir supprimer le client : " + client.getNom() + " " + client.getPrenom() + " ?",
                    "Confirmation", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                if (clientService.supprimerClient(login)) {
                    refreshClientTable(model);
                    JOptionPane.showMessageDialog(frame, "Client supprimé avec succès");
                } else {
                    JOptionPane.showMessageDialog(frame, "Erreur lors de la suppression", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private static JPanel createGestionArticlesPanel(JPanel contentPanel) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(LIGHT_COLOR);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // tableau des articles 
        String[] columnNames = {"ID", "Nom", "Description", "Prix", "Catégorie", "Image"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 5) return ImageIcon.class;
                return super.getColumnClass(columnIndex);
            }
        };
        
        JTable table = new JTable(model) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (column == 5) { 
                    ((JLabel)c).setHorizontalAlignment(SwingConstants.CENTER);
                }
                return c;
            }
        };
        
        table.setDefaultRenderer(ImageIcon.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
                                                         boolean hasFocus, int row, int column) {
                JLabel label = new JLabel();
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setOpaque(true);
                
                if (isSelected) {
                    label.setBackground(table.getSelectionBackground());
                    label.setForeground(table.getSelectionForeground());
                } else {
                    label.setBackground(table.getBackground());
                    label.setForeground(table.getForeground());
                }
                
                if (value != null) {
                    ImageIcon icon = (ImageIcon) value;
                    if (icon.getImage() != null) {
                        Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                        label.setIcon(new ImageIcon(img));
                    } else {
                        label.setIcon(imageIcon); 
                    }
                } else {
                    label.setIcon(imageIcon); 
                }
                
                return label;
            }
        });
        
        table.setFont(new Font("Poppins", Font.PLAIN, 14));
        table.setRowHeight(85);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            if (i != 2 && i != 5) { 
                table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(PRIMARY_COLOR, 2), 
            "Liste des articles", 
            javax.swing.border.TitledBorder.LEFT, 
            javax.swing.border.TitledBorder.TOP,
            new Font("Poppins", Font.BOLD, 18),
            PRIMARY_COLOR));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        buttonPanel.setBackground(LIGHT_COLOR);
        
        JButton refreshButton = createStyledButton("  Actualiser", PRIMARY_COLOR, refreshIcon);
        refreshButton.setFont(new Font("Poppins", Font.BOLD, 16));
        JButton addButton = createStyledButton("  Ajouter", PRIMARY_COLOR, addIcon);
        addButton.setFont(new Font("Poppins", Font.BOLD, 16));
        JButton editButton = createStyledButton("  Modifier", PRIMARY_COLOR, editIcon);
        editButton.setFont(new Font("Poppins", Font.BOLD, 16));
        JButton deleteButton = createStyledButton("  Supprimer", ACCENT_COLOR, deleteIcon);
        deleteButton.setFont(new Font("Poppins", Font.BOLD, 16));
        JButton backButton = createStyledButton("  Retour", DARK_COLOR, homeIcon);
        backButton.setFont(new Font("Poppins", Font.BOLD, 16));

        refreshButton.addActionListener(e -> refreshArticleTable(model));
        addButton.addActionListener(e -> showAddArticleDialog(model));
        editButton.addActionListener(e -> askArticleIdAndEdit(model));
        deleteButton.addActionListener(e -> askArticleIdAndDelete(model));
        backButton.addActionListener(e -> {
            CardLayout cl = (CardLayout)(contentPanel.getLayout());
            cl.show(contentPanel, "MENU_GERANT");
        });

        buttonPanel.add(refreshButton);
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        refreshArticleTable(model);

        return panel;
    }

    private static void refreshArticleTable(DefaultTableModel model) {
        model.setRowCount(0);
        List<Article> articles = articleService.listerArticles();
        for (Article article : articles) {
            ImageIcon image = imageIcon; 
            
            try {
                if (article.getImageUrl() != null && !article.getImageUrl().isEmpty()) {
                    if (article.getImageUrl().startsWith("http")) {
                        URL url = new URL(article.getImageUrl());
                        Image originalImage = ImageIO.read(url);
                        if (originalImage != null) {
                            Image scaledImage = originalImage.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                            image = new ImageIcon(scaledImage);
                        }
                    } else {
                        File file = new File(article.getImageUrl());
                        if (file.exists()) {
                            Image originalImage = ImageIO.read(file);
                            if (originalImage != null) {
                                Image scaledImage = originalImage.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                                image = new ImageIcon(scaledImage);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                System.err.println("Erreur lors du chargement de l'image: " + e.getMessage());
                image = imageIcon; 
            }
            
            model.addRow(new Object[]{
                article.getId(),
                article.getNom(),
                article.getDescription(),
                String.format("%.2f DT", article.getPrix()),
                article.getCategorie(),
                image
            });
        }
    }

    private static void showAddArticleDialog(DefaultTableModel model) {
        JDialog dialog = new JDialog(frame, "Ajouter un article", true);
        dialog.setSize(600, 500);
        dialog.setLocationRelativeTo(frame);
        dialog.setLayout(new BorderLayout());
        dialog.getContentPane().setBackground(LIGHT_COLOR);

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 15, 15));
        formPanel.setBackground(LIGHT_COLOR);
        formPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        JLabel nomLabel = new JLabel("Nom:");
        nomLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        JTextField nomField = new JTextField();
        nomField.setFont(new Font("Poppins", Font.PLAIN, 16));
        
        JLabel descLabel = new JLabel("Description:");
        descLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        JTextField descField = new JTextField();
        descField.setFont(new Font("Poppins", Font.PLAIN, 16));
        
        JLabel prixLabel = new JLabel("Prix:");
        prixLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        JTextField prixField = new JTextField();
        prixField.setFont(new Font("Poppins", Font.PLAIN, 16));
        
        JLabel categLabel = new JLabel("Catégorie:");
        categLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        JComboBox<String> categCombo = new JComboBox<>(new String[]{"Plat", "Sandwich", "Salade", "Dessert", "Viennoiserie", "Boisson"});
        categCombo.setFont(new Font("Poppins", Font.PLAIN, 16));
        
        JLabel imageLabel = new JLabel("URL Image:");
        imageLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        JTextField imageField = new JTextField();
        imageField.setFont(new Font("Poppins", Font.PLAIN, 16));
        
        JButton browseButton = createStyledButton("Parcourir...", PRIMARY_COLOR, null);
        browseButton.setFont(new Font("Poppins", Font.BOLD, 14));
        browseButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Images", "jpg", "jpeg", "png", "gif"));
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                imageField.setText(selectedFile.getAbsolutePath());
            }
        });

        formPanel.add(nomLabel);
        formPanel.add(nomField);
        formPanel.add(descLabel);
        formPanel.add(descField);
        formPanel.add(prixLabel);
        formPanel.add(prixField);
        formPanel.add(categLabel);
        formPanel.add(categCombo);
        formPanel.add(imageLabel);
        formPanel.add(imageField);
        formPanel.add(new JLabel());
        formPanel.add(browseButton);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        buttonPanel.setBackground(LIGHT_COLOR);
        JButton saveButton = createStyledButton("Enregistrer", PRIMARY_COLOR, null);
        saveButton.setFont(new Font("Poppins", Font.BOLD, 16));
        JButton cancelButton = createStyledButton("Annuler", DARK_COLOR, null);
        cancelButton.setFont(new Font("Poppins", Font.BOLD, 16));

        saveButton.addActionListener(e -> {
            try {
                Article article = new Article(
                    0, 
                    nomField.getText(),
                    descField.getText(),
                    Double.parseDouble(prixField.getText()),
                    (String) categCombo.getSelectedItem(),
                    imageField.getText()
                );

                if (articleService.ajouterArticle(article)) {
                    refreshArticleTable(model);
                    dialog.dispose();
                    JOptionPane.showMessageDialog(frame, "Article ajouté avec succès");
                } else {
                    JOptionPane.showMessageDialog(frame, "Erreur lors de l'ajout de l'article", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Données invalides", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private static void askArticleIdAndDelete(DefaultTableModel model) {
        JTextField idInput = new JTextField();
        idInput.setFont(new Font("Poppins", Font.PLAIN, 16));

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Saisir l'ID de l'article à supprimer:"), BorderLayout.NORTH);
        panel.add(idInput, BorderLayout.CENTER);

        int result = JOptionPane.showConfirmDialog(frame, panel,
                "Supprimer un article", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(idInput.getText().trim());

                Article article = articleService.trouverArticleParId(id);
                if (article == null) {
                    JOptionPane.showMessageDialog(frame, "Aucun article trouvé avec cet ID", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(frame,
                        "Êtes-vous sûr de vouloir supprimer l'article : " + article.getNom() + " ?",
                        "Confirmation", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    if (articleService.supprimerArticle(id)) {
                        refreshArticleTable(model);
                        JOptionPane.showMessageDialog(frame, "Article supprimé avec succès");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Erreur lors de la suppression", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "ID invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void askArticleIdAndEdit(DefaultTableModel model) {
        JTextField idInput = new JTextField();
        idInput.setFont(new Font("Poppins", Font.PLAIN, 16));

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Saisir l'ID de l'article à modifier:"), BorderLayout.NORTH);
        panel.add(idInput, BorderLayout.CENTER);

        int result = JOptionPane.showConfirmDialog(frame, panel,
                "Modifier un article", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(idInput.getText().trim());
                showEditArticleDialog(id, model);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "ID invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void showEditArticleDialog(int id, DefaultTableModel model) {
    	   Article article = articleService.trouverArticleParId(id);
    	   if (article == null) return;

    	   JDialog dialog = new JDialog(frame, "Modifier un article", true);
    	   dialog.setSize(600, 500);
    	   dialog.setLocationRelativeTo(frame);
    	   dialog.setLayout(new BorderLayout());
    	   dialog.getContentPane().setBackground(LIGHT_COLOR);

    	   JPanel formPanel = new JPanel();
    	   formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
    	   formPanel.setBackground(LIGHT_COLOR);
    	   formPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

    	   JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	   idPanel.setBackground(LIGHT_COLOR);
    	   JLabel idLabel = new JLabel("ID:");
    	   idLabel.setFont(new Font("Poppins", Font.BOLD, 16));
    	   JTextField idField = new JTextField(String.valueOf(article.getId()));
    	   idField.setFont(new Font("Poppins", Font.PLAIN, 16));
    	   idField.setEditable(false);
    	   idPanel.add(idLabel);
    	   idPanel.add(idField);
    	   formPanel.add(idPanel);

    	   // panel pour le nom
    	   JPanel nomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	   nomPanel.setBackground(LIGHT_COLOR);
    	   JLabel nomLabel = new JLabel("Nom:");
    	   nomLabel.setFont(new Font("Poppins", Font.BOLD, 16));
    	   JTextField nomField = new JTextField(article.getNom());
    	   nomField.setFont(new Font("Poppins", Font.PLAIN, 16));
    	   nomField.setColumns(20);
    	   nomPanel.add(nomLabel);
    	   nomPanel.add(nomField);
    	   formPanel.add(nomPanel);

    	   // panel pour la description
    	   JPanel descPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	   descPanel.setBackground(LIGHT_COLOR);
    	   JLabel descLabel = new JLabel("Description:");
    	   descLabel.setFont(new Font("Poppins", Font.BOLD, 16));
    	   JTextArea descArea = new JTextArea(article.getDescription(), 3, 20);
    	   descArea.setFont(new Font("Poppins", Font.PLAIN, 16));
    	   descArea.setLineWrap(true);
    	   descArea.setWrapStyleWord(true);
    	   JScrollPane descScroll = new JScrollPane(descArea);
    	   descPanel.add(descLabel);
    	   descPanel.add(descScroll);
    	   formPanel.add(descPanel);

    	   // panel pour le prix
    	   JPanel prixPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	   prixPanel.setBackground(LIGHT_COLOR);
    	   JLabel prixLabel = new JLabel("Prix:");
    	   prixLabel.setFont(new Font("Poppins", Font.BOLD, 16));
    	   JTextField prixField = new JTextField(String.valueOf(article.getPrix()));
    	   prixField.setFont(new Font("Poppins", Font.PLAIN, 16));
    	   prixField.setColumns(10);
    	   prixPanel.add(prixLabel);
    	   prixPanel.add(prixField);
    	   formPanel.add(prixPanel);

    	// panel pour la catégorie
    	   JPanel categPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	   categPanel.setBackground(LIGHT_COLOR);
    	   JLabel categLabel = new JLabel("Catégorie:");
    	   categLabel.setFont(new Font("Poppins", Font.BOLD, 16));
    	   JComboBox<String> categCombo = new JComboBox<>(new String[]{"Plat", "Sandwich", "Salade", "Dessert", "Viennoiserie", "Boisson"});
    	   categCombo.setFont(new Font("Poppins", Font.PLAIN, 16));

    	   String currentCategory = article.getCategorie();
    	   if (currentCategory != null) {
    	       categCombo.setSelectedItem(currentCategory);
    	   } else {
    	       categCombo.setSelectedItem("Plat");
    	   }

    	   categPanel.add(categLabel);
    	   categPanel.add(categCombo);
    	   formPanel.add(categPanel);

    	   JPanel imagePanel = new JPanel(new BorderLayout(10, 10));
    	   imagePanel.setBackground(LIGHT_COLOR);
    	   JLabel imageLabel = new JLabel("Image:");
    	   imageLabel.setFont(new Font("Poppins", Font.BOLD, 16));
    	   
    	   JPanel imageControlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	   imageControlPanel.setBackground(LIGHT_COLOR);
    	   
    	   JTextField imageField = new JTextField(article.getImageUrl());
    	   imageField.setFont(new Font("Poppins", Font.PLAIN, 16));
    	   imageField.setColumns(20);
    	   
    	   JButton browseButton = createStyledButton("Parcourir...", PRIMARY_COLOR, null);
    	   browseButton.setFont(new Font("Poppins", Font.BOLD, 14));
    	   browseButton.addActionListener(e -> {
    	       JFileChooser fileChooser = new JFileChooser();
    	       fileChooser.setFileFilter(new FileNameExtensionFilter("Images", "jpg", "jpeg", "png", "gif"));
    	       int returnValue = fileChooser.showOpenDialog(null);
    	       if (returnValue == JFileChooser.APPROVE_OPTION) {
    	           File selectedFile = fileChooser.getSelectedFile();
    	           imageField.setText(selectedFile.getAbsolutePath());
    	       }
    	   });
    	   
    	   imageControlPanel.add(imageLabel);
    	   imageControlPanel.add(imageField);
    	   imageControlPanel.add(browseButton);
    	   
    	   JLabel imagePreview = new JLabel();
    	   imagePreview.setHorizontalAlignment(SwingConstants.CENTER);
    	   imagePreview.setPreferredSize(new Dimension(150, 150));
    	   try {
    	       if (article.getImageUrl() != null && !article.getImageUrl().isEmpty()) {
    	           Image img;
    	           if (article.getImageUrl().startsWith("http")) {
    	               img = ImageIO.read(new URL(article.getImageUrl()));
    	           } else {
    	               img = ImageIO.read(new File(article.getImageUrl()));
    	           }
    	           if (img != null) {
    	               Image scaledImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
    	               imagePreview.setIcon(new ImageIcon(scaledImg));
    	           }
    	       }
    	   } catch (Exception e) {
    	       imagePreview.setIcon(imageIcon);
    	   }
    	   
    	   imagePanel.add(imageControlPanel, BorderLayout.NORTH);
    	   imagePanel.add(imagePreview, BorderLayout.CENTER);
    	   formPanel.add(imagePanel);

    	   JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
    	   buttonPanel.setBackground(LIGHT_COLOR);
    	   JButton saveButton = createStyledButton("Enregistrer", PRIMARY_COLOR, null);
    	   saveButton.setFont(new Font("Poppins", Font.BOLD, 16));
    	   JButton cancelButton = createStyledButton("Annuler", DARK_COLOR, null);
    	   cancelButton.setFont(new Font("Poppins", Font.BOLD, 16));

    	   saveButton.addActionListener(e -> {
    	       try {
    	           article.setNom(nomField.getText());
    	           article.setDescription(descArea.getText());
    	           article.setPrix(Double.parseDouble(prixField.getText()));
    	           article.setCategorie((String) categCombo.getSelectedItem());
    	           article.setImageUrl(imageField.getText());

    	           if (articleService.modifierArticle(article)) {
    	               refreshArticleTable(model);
    	               dialog.dispose();
    	               JOptionPane.showMessageDialog(frame, "Article modifié avec succès");
    	           } else {
    	               JOptionPane.showMessageDialog(frame,
    	                   "Erreur lors de la modification de l'article",
    	                   "Erreur", JOptionPane.ERROR_MESSAGE);
    	           }
    	       } catch (Exception ex) {
    	           JOptionPane.showMessageDialog(frame,
    	               "Données invalides",
    	               "Erreur", JOptionPane.ERROR_MESSAGE);
    	       }
    	   });

    	   cancelButton.addActionListener(e -> dialog.dispose());

    	   buttonPanel.add(saveButton);
    	   buttonPanel.add(cancelButton);

    	   JScrollPane scrollPane = new JScrollPane(formPanel);
    	   scrollPane.setBorder(null);

    	   dialog.add(scrollPane, BorderLayout.CENTER);
    	   dialog.add(buttonPanel, BorderLayout.SOUTH);
    	   dialog.setVisible(true);
    	}

    
    private static JPanel createGestionCommandesPanel(JPanel contentPanel) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(LIGHT_COLOR);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // tableau des commandes
        String[] columnNames = {"ID", "Client", "Date", "Statut", "Type", "Total", "Récupérée"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 6) return Boolean.class;
                return super.getColumnClass(columnIndex);
            }
        };
        
        JTable table = new JTable(model);
        table.setFont(new Font("Poppins", Font.PLAIN, 14));
        table.setRowHeight(35);
        table.setSelectionBackground(SECONDARY_COLOR);
        table.setSelectionForeground(DARK_COLOR);
        table.setGridColor(new Color(200, 200, 200));
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            if (i != 1 && i != 2) { 
                table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(PRIMARY_COLOR, 2), 
            "Liste des commandes", 
            javax.swing.border.TitledBorder.LEFT, 
            javax.swing.border.TitledBorder.TOP,
            new Font("Poppins", Font.BOLD, 18),
            PRIMARY_COLOR));

        // boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        buttonPanel.setBackground(LIGHT_COLOR);
        
        JButton refreshButton = createStyledButton("Actualiser", PRIMARY_COLOR, refreshIcon);
        refreshButton.setFont(new Font("Poppins", Font.BOLD, 16));
        JButton detailsButton = createStyledButton("Détails", PRIMARY_COLOR, detailIcon);
        detailsButton.setFont(new Font("Poppins", Font.BOLD, 16));
        JButton statusButton = createStyledButton("Changer statut", PRIMARY_COLOR, statusIcon);
        statusButton.setFont(new Font("Poppins", Font.BOLD, 16));
        JButton recupereeButton = createStyledButton("Marquer récupérée", PRIMARY_COLOR, statusIcon);
        recupereeButton.setFont(new Font("Poppins", Font.BOLD, 16));
        JButton deleteButton = createStyledButton("Supprimer", ACCENT_COLOR, deleteIcon);
        deleteButton.setFont(new Font("Poppins", Font.BOLD, 16));
        JButton backButton = createStyledButton("Retour", DARK_COLOR, homeIcon);
        backButton.setFont(new Font("Poppins", Font.BOLD, 16));

        refreshButton.addActionListener(e -> refreshCommandeTable(model));
        detailsButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int id = (int) model.getValueAt(selectedRow, 0);
                showCommandeDetails(id);
            } else {
                JOptionPane.showMessageDialog(frame, "Veuillez sélectionner une commande", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        statusButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int id = (int) model.getValueAt(selectedRow, 0);
                changeCommandeStatus(id, model);
            } else {
                JOptionPane.showMessageDialog(frame, "Veuillez sélectionner une commande", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        recupereeButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int id = (int) model.getValueAt(selectedRow, 0);
                Commande commande = commandeService.trouverCommandeParId(id);
                if (commande != null) {
                    if (commande.getStatut().equals("Prête")) {
                        commande.setRecuperee(!commande.isRecuperee());
                        commandeService.modifierCommande(commande);
                        refreshCommandeTable(model);
                    } else {
                        JOptionPane.showMessageDialog(frame, 
                            "Seules les commandes avec statut 'Prête' peuvent être marquées comme récupérées", 
                            "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Veuillez sélectionner une commande", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int id = (int) model.getValueAt(selectedRow, 0);
                int confirm = JOptionPane.showConfirmDialog(frame, 
                    "Êtes-vous sûr de vouloir supprimer cette commande?", 
                    "Confirmation", JOptionPane.YES_NO_OPTION);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    if (commandeService.supprimerCommande(id)) {
                        refreshCommandeTable(model);
                        JOptionPane.showMessageDialog(frame, "Commande supprimée avec succès");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Erreur lors de la suppression", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Veuillez sélectionner une commande", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> {
            CardLayout cl = (CardLayout)(contentPanel.getLayout());
            cl.show(contentPanel, "MENU_GERANT");
        });

        buttonPanel.add(refreshButton);
        buttonPanel.add(detailsButton);
        buttonPanel.add(statusButton);
        buttonPanel.add(recupereeButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        refreshCommandeTable(model);

        return panel;
    }

    private static void refreshCommandeTable(DefaultTableModel model) {
        model.setRowCount(0);
        List<Commande> commandes = commandeService.listerCommandes();
        Map<Integer, Article> articles = commandeService.getArticlesMap();

        for (Commande commande : commandes) {
            double total = commande.calculerTotal(articles);
            model.addRow(new Object[]{
                commande.getId(),
                commande.getClientLogin(),
                commande.getDateHeure(),
                commande.getStatut(),
                commande.getType(),
                String.format("%.2f DT", total),
                commande.isRecuperee()
            });
        }
    }

    private static void showCommandeDetails(int id) {
        Commande commande = commandeService.trouverCommandeParId(id);
        if (commande == null) return;

        Map<Integer, Article> articles = commandeService.getArticlesMap();
        double total = commande.calculerTotal(articles);

        JDialog dialog = new JDialog(frame, "Détails de la commande #" + id, true);
        dialog.setSize(600, 500);
        dialog.setLocationRelativeTo(frame);
        dialog.setLayout(new BorderLayout());
        dialog.getContentPane().setBackground(LIGHT_COLOR);

        JPanel detailsPanel = new JPanel(new BorderLayout());
        detailsPanel.setBackground(LIGHT_COLOR);
        detailsPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel infoPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        infoPanel.setBackground(LIGHT_COLOR);
        
        infoPanel.add(createInfoLabel("Client: " + commande.getClientLogin()));
        infoPanel.add(createInfoLabel("Date: " + commande.getDateHeure()));
        infoPanel.add(createInfoLabel("Statut: " + commande.getStatut()));
        infoPanel.add(createInfoLabel("Type: " + commande.getType()));
        infoPanel.add(createInfoLabel("Récupérée: " + (commande.isRecuperee() ? "Oui" : "Non")));
        infoPanel.add(createInfoLabel("Total: " + String.format("%.2f DT", total)));

        JPanel articlesPanel = new JPanel(new BorderLayout());
        articlesPanel.setBackground(LIGHT_COLOR);
        articlesPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
        
        JLabel articlesLabel = new JLabel("Articles:");
        articlesLabel.setFont(new Font("Poppins", Font.BOLD, 18));
        articlesLabel.setForeground(PRIMARY_COLOR);
        articlesPanel.add(articlesLabel, BorderLayout.NORTH);
        
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> articlesList = new JList<>(listModel);
        articlesList.setBackground(LIGHT_COLOR);
        articlesList.setFont(new Font("Poppins", Font.PLAIN, 16));
        
        for (Map.Entry<Integer, Integer> entry : commande.getArticlesQuantites().entrySet()) {
            Article article = articles.get(entry.getKey());
            if (article != null) {
                listModel.addElement(String.format("%d x %s - %.2f DT", 
                    entry.getValue(), article.getNom(), article.getPrix()));
            }
        }
        
        JScrollPane scrollPane = new JScrollPane(articlesList);
        articlesPanel.add(scrollPane, BorderLayout.CENTER);

        detailsPanel.add(infoPanel, BorderLayout.NORTH);
        detailsPanel.add(articlesPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(LIGHT_COLOR);
        JButton closeButton = createStyledButton("Fermer", PRIMARY_COLOR, null);
        closeButton.setFont(new Font("Poppins", Font.BOLD, 16));
        closeButton.addActionListener(e -> dialog.dispose());
        buttonPanel.add(closeButton);

        dialog.add(detailsPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private static JLabel createInfoLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Poppins", Font.PLAIN, 16));
        return label;
    }

    private static void changeCommandeStatus(int id, DefaultTableModel model) {
        Commande commande = commandeService.trouverCommandeParId(id);
        if (commande == null) return;

        List<String> statuts = commandeService.getStatutsPossibles();
        
        JDialog dialog = new JDialog(frame, "Changer le statut de la commande #" + id, true);
        dialog.setSize(500, 250);
        dialog.setLocationRelativeTo(frame);
        dialog.setLayout(new BorderLayout());
        dialog.getContentPane().setBackground(LIGHT_COLOR);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(LIGHT_COLOR);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel currentLabel = new JLabel("Statut actuel: " + commande.getStatut());
        currentLabel.setFont(new Font("Poppins", Font.BOLD, 18));
        currentLabel.setForeground(PRIMARY_COLOR);
        panel.add(currentLabel, BorderLayout.NORTH);

        JLabel newLabel = new JLabel("Nouveau statut:");
        newLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        JComboBox<String> statusCombo = new JComboBox<>(statuts.toArray(new String[0]));
        statusCombo.setFont(new Font("Poppins", Font.PLAIN, 16));
        statusCombo.setSelectedItem(commande.getStatut());
        
        JPanel comboPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));
        comboPanel.setBackground(LIGHT_COLOR);
        comboPanel.add(newLabel);
        comboPanel.add(statusCombo);
        panel.add(comboPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        buttonPanel.setBackground(LIGHT_COLOR);
        JButton saveButton = createStyledButton("Enregistrer", PRIMARY_COLOR, null);
        saveButton.setFont(new Font("Poppins", Font.BOLD, 16));
        JButton cancelButton = createStyledButton("Annuler", DARK_COLOR, null);
        cancelButton.setFont(new Font("Poppins", Font.BOLD, 16));

        saveButton.addActionListener(e -> {
            String newStatus = (String) statusCombo.getSelectedItem();
            if (commandeService.modifierStatutCommande(id, newStatus)) {
                refreshCommandeTable(model);
                dialog.dispose();
                JOptionPane.showMessageDialog(frame, "Statut modifié avec succès");
            } else {
                JOptionPane.showMessageDialog(frame, "Erreur lors de la modification du statut", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private static JPanel createLoginClientPanel(JPanel contentPanel) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(LIGHT_COLOR);
        panel.setBorder(new EmptyBorder(50, 50, 50, 50));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(LIGHT_COLOR);
        formPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(PRIMARY_COLOR, 2), 
            "Connexion Client", 
            javax.swing.border.TitledBorder.CENTER, 
            javax.swing.border.TitledBorder.TOP,
            new Font("Poppins", Font.BOLD, 24),
            PRIMARY_COLOR));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel loginLabel = new JLabel("Login:");
        loginLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        formPanel.add(loginLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        JTextField loginField = new JTextField(20);
        loginField.setFont(new Font("Poppins", Font.PLAIN, 16));
        loginField.setPreferredSize(new Dimension(300, 35));
        formPanel.add(loginField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        JLabel passwordLabel = new JLabel("Mot de passe:");
        passwordLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        formPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Poppins", Font.PLAIN, 16));
        passwordField.setPreferredSize(new Dimension(300, 35));
        formPanel.add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy++;
        gbc.gridwidth = 1;
        JButton loginButton = createStyledButton("Se connecter", PRIMARY_COLOR, loginIcon);
        loginButton.setFont(new Font("Poppins", Font.BOLD, 16));
        loginButton.setPreferredSize(new Dimension(200, 45));
        loginButton.addActionListener(e -> {
            String login = loginField.getText();
            String password = new String(passwordField.getPassword());
            
            Client client = clientService.authentifier(login, password);
            if (client != null) {
                if (client.isPremiereConnexion()) {
                    String nouveauMdp = JOptionPane.showInputDialog(frame, "Ceci est votre première connexion.\nVeuillez saisir un nouveau mot de passe :");
                    if (nouveauMdp != null && !nouveauMdp.trim().isEmpty()) {
                        client.setPassword(nouveauMdp.trim());
                        client.setPremiereConnexion(false);
                        clientService.modifierClient(client);
                        JOptionPane.showMessageDialog(frame, "Mot de passe changé avec succès !");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Changement de mot de passe annulé. Connexion échouée.", "Avertissement", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                Main.clientConnecte = client;
                CardLayout cl = (CardLayout)(contentPanel.getLayout());
                cl.show(contentPanel, "MENU_CLIENT");
            } else {
                JOptionPane.showMessageDialog(frame, "Identifiants incorrects!", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
        formPanel.add(loginButton, gbc);

        gbc.gridx = 2;
        JButton backButton = createStyledButton("Retour", DARK_COLOR, homeIcon);
        backButton.setFont(new Font("Poppins", Font.BOLD, 16));
        backButton.setPreferredSize(new Dimension(200, 45));
        backButton.addActionListener(e -> {
            CardLayout cl = (CardLayout)(contentPanel.getLayout());
            cl.show(contentPanel, "HOME");
        });
        formPanel.add(backButton, gbc);

        panel.add(formPanel, BorderLayout.CENTER);

        return panel;
    }

    private static JPanel createMenuClientPanel(JPanel contentPanel) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(LIGHT_COLOR);
        panel.setBorder(new EmptyBorder(30, 50, 50, 50));

        JPanel welcomePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        welcomePanel.setBackground(LIGHT_COLOR);
        
        JLabel welcomeLabel = new JLabel("Bienvenue " + (clientConnecte != null ? clientConnecte.getPrenom() : "") + " dans l'espace Client");
        welcomeLabel.setFont(new Font("Poppins", Font.BOLD, 24));
        welcomeLabel.setForeground(PRIMARY_COLOR);
        welcomeLabel.setBorder(new EmptyBorder(0, 0, 30, 0));
        
        welcomePanel.add(welcomeLabel);

        JPanel cardPanel = new JPanel(new GridBagLayout());
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(PRIMARY_COLOR, 2),
            new EmptyBorder(40, 40, 40, 40)
        ));

        // Panel des boutons
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 0, 25));
        buttonPanel.setBackground(Color.WHITE);

        JButton menuButton = createStyledButton("Consulter le menu", PRIMARY_COLOR, menuIcon);
        menuButton.setFont(new Font("Poppins", Font.BOLD, 18));
        menuButton.setPreferredSize(new Dimension(350, 60));
        menuButton.addActionListener(e -> showMenuDialog());

        JButton orderButton = createStyledButton("Passer une commande", PRIMARY_COLOR, cartIcon);
        orderButton.setFont(new Font("Poppins", Font.BOLD, 18));
        orderButton.setPreferredSize(new Dimension(350, 60));
        orderButton.addActionListener(e -> showOrderDialog());

        JButton ordersButton = createStyledButton("Voir mes commandes", PRIMARY_COLOR, orderIcon);
        ordersButton.setFont(new Font("Poppins", Font.BOLD, 18));
        ordersButton.setPreferredSize(new Dimension(350, 60));
        ordersButton.addActionListener(e -> showClientOrdersDialog());

        JButton infoButton = createStyledButton("Modifier mes informations", PRIMARY_COLOR, userIcon);
        infoButton.setFont(new Font("Poppins", Font.BOLD, 18));
        infoButton.setPreferredSize(new Dimension(350, 60));
        infoButton.addActionListener(e -> showEditClientInfoDialog());

        JButton backButton = createStyledButton("Déconnexion", DARK_COLOR, logoutIcon);
        backButton.setFont(new Font("Poppins", Font.BOLD, 18));
        backButton.setPreferredSize(new Dimension(350, 60));
        backButton.addActionListener(e -> {
            clientConnecte = null;
            CardLayout cl = (CardLayout)(contentPanel.getLayout());
            cl.show(contentPanel, "HOME");
        });

        buttonPanel.add(menuButton);
        buttonPanel.add(orderButton);
        buttonPanel.add(ordersButton);
        buttonPanel.add(infoButton);
        buttonPanel.add(backButton);

        cardPanel.add(buttonPanel);
        panel.add(welcomePanel, BorderLayout.NORTH);
        panel.add(cardPanel, BorderLayout.CENTER);

        return panel;
    }

    private static void showMenuDialog() {
        List<Article> articles = articleService.listerArticles();
        if (articles.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Aucun article disponible pour le moment.", "Menu", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // les articles par catégorie
        Map<String, List<Article>> articlesParCategorie = new LinkedHashMap<>();
        for (Article article : articles) {
            articlesParCategorie.computeIfAbsent(article.getCategorie(), k -> new ArrayList<>()).add(article);
        }

        JDialog dialog = new JDialog(frame, "Menu du restaurant", true);
        dialog.setSize(1000, 800);
        dialog.setLocationRelativeTo(frame);
        dialog.setLayout(new BorderLayout());

        JLabel backgroundLabel = new JLabel();
        try {
            Image bg = ImageIO.read(new File(MENU_BACKGROUND_PATH));
            backgroundLabel.setIcon(new ImageIcon(bg.getScaledInstance(1000, 800, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            backgroundLabel.setBackground(new Color(240, 240, 240));
            backgroundLabel.setOpaque(true);
            System.err.println("Erreur chargement image fond : " + e.getMessage());
        }
        backgroundLabel.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);


        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setOpaque(false);
        headerPanel.setBorder(new EmptyBorder(30, 0, 20, 0));

        JLabel titleLabel = new JLabel("NOTRE MENU");
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 48));
        titleLabel.setForeground(GOLD_COLOR);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel subtitleLabel = new JLabel("Découvrez nos délices culinaires");
        subtitleLabel.setFont(new Font("Poppins", Font.ITALIC, 22));
        subtitleLabel.setForeground(Color.WHITE);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(10));
        headerPanel.add(subtitleLabel);


        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.setFont(new Font("Poppins", Font.BOLD, 16));
        tabbedPane.setBackground(new Color(0, 0, 0, 150));
        tabbedPane.setForeground(Color.WHITE);
        
        tabbedPane.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI() {
            @Override
            protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, 
                                            int x, int y, int w, int h, boolean isSelected) {
                Graphics2D g2d = (Graphics2D) g.create();
                
                if (isSelected) {
                    g2d.setColor(new Color(255, 215, 0, 200)); 
                } else {
                    g2d.setColor(new Color(70, 70, 70, 180)); 
                }
                
                g2d.fillRoundRect(x, y, w, h, 15, 15);
                g2d.dispose();
            }
            
            @Override
            protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, 
                                         int x, int y, int w, int h, boolean isSelected) {
            }
            
            @Override
            protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
                return 40;
            }
        });

        for (String categorie : articlesParCategorie.keySet()) {
            JPanel categoryPanel = new JPanel(new GridLayout(0, 3, 20, 20));
            categoryPanel.setOpaque(false);
            categoryPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

            for (Article article : articlesParCategorie.get(categorie)) {
                // carte d'article
                JPanel card = new JPanel();
                card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
                card.setBackground(new Color(255, 255, 255, 230)); // Blanc semi-transparent
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(255, 215, 0, 200), 2), // Bordure or
                    new EmptyBorder(15, 15, 15, 15)
                ));
                card.setMaximumSize(new Dimension(280, 280));

                // image de l'article 
                JPanel imagePanel = new JPanel(new BorderLayout());
                imagePanel.setBackground(new Color(240, 240, 240));
                imagePanel.setPreferredSize(new Dimension(220, 220));
                imagePanel.setMaximumSize(new Dimension(220, 220));
                imagePanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

                JLabel imageLabel = new JLabel();
                imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                try {
                    if (article.getImageUrl() != null && !article.getImageUrl().isEmpty()) {
                        Image img = article.getImageUrl().startsWith("http")
                            ? ImageIO.read(new URL(article.getImageUrl()))
                            : ImageIO.read(new File(article.getImageUrl()));
                        
                        BufferedImage thumbnail = new BufferedImage(220, 220, BufferedImage.TYPE_INT_ARGB);
                        Graphics2D g2d = thumbnail.createGraphics();
                        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                        
                        int width = img.getWidth(null);
                        int height = img.getHeight(null);
                        float ratio = Math.min(220f / width, 220f / height);
                        int newWidth = (int) (width * ratio);
                        int newHeight = (int) (height * ratio);
                        int x = (220 - newWidth) / 2;
                        int y = (220 - newHeight) / 2;
                        
                        g2d.drawImage(img, x, y, newWidth, newHeight, null);
                        g2d.dispose();
                        
                        imageLabel.setIcon(new ImageIcon(thumbnail));
                    } else {
                        imageLabel.setIcon(imageIcon);
                    }
                } catch (Exception e) {
                    imageLabel.setIcon(imageIcon);
                }
                imagePanel.add(imageLabel, BorderLayout.CENTER);

                // nom et prix
                JPanel infoPanel = new JPanel();
                infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
                infoPanel.setOpaque(false);
                infoPanel.setBorder(new EmptyBorder(10, 0, 0, 0));

                JLabel nameLabel = new JLabel(article.getNom());
                nameLabel.setFont(new Font("Poppins", Font.BOLD, 18));
                nameLabel.setForeground(new Color(50, 50, 50));
                nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                JLabel priceLabel = new JLabel(String.format("%.2f DT", article.getPrix()));
                priceLabel.setFont(new Font("Poppins", Font.BOLD, 16));
                priceLabel.setForeground(new Color(200, 50, 30));
                priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                // bouton description
                JButton descButton = new JButton("Description");
                descButton.setFont(new Font("Poppins", Font.PLAIN, 14));
                descButton.setBackground(GOLD_COLOR);
                descButton.setForeground(Color.BLACK);
                descButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
                descButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                descButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                descButton.addActionListener(e -> {
                    JOptionPane.showMessageDialog(dialog,
                        "<html><div style='width:300px;text-align:center'>" +
                        "<h2 style='color:#78321e;margin-bottom:10px'>" + article.getNom() + "</h2>" +
                        "<p style='font-size:14px;color:#555;margin-bottom:15px'>" + article.getDescription() + "</p>" +
                        "<p style='text-align:right;font-weight:bold;color:#c83214;font-size:16px'>" + 
                        String.format("%.2f DT", article.getPrix()) + "</p></div></html>",
                        "Détails de l'article",
                        JOptionPane.INFORMATION_MESSAGE);
                });

                infoPanel.add(nameLabel);
                infoPanel.add(Box.createVerticalStrut(5));
                infoPanel.add(priceLabel);
                infoPanel.add(Box.createVerticalStrut(10));
                infoPanel.add(descButton);

                card.add(imagePanel);
                card.add(infoPanel);
                categoryPanel.add(card);
            }

            JScrollPane scrollPane = new JScrollPane(categoryPanel);
            scrollPane.setOpaque(false);
            scrollPane.getViewport().setOpaque(false);
            scrollPane.setBorder(null);

            tabbedPane.addTab(categorie.toUpperCase(), scrollPane);
        }


        JButton closeButton = new JButton("Fermer le menu");
        closeButton.setFont(new Font("Poppins", Font.BOLD, 16));
        closeButton.setForeground(Color.WHITE);
        closeButton.setBackground(new Color(120, 50, 30));
        closeButton.setFocusPainted(false);
        closeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeButton.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        closeButton.addActionListener(e -> dialog.dispose());
        closeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                closeButton.setBackground(new Color(150, 60, 40));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                closeButton.setBackground(new Color(120, 50, 30));
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(closeButton);

        contentPanel.add(headerPanel, BorderLayout.NORTH);
        contentPanel.add(tabbedPane, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        backgroundLabel.add(contentPanel, BorderLayout.CENTER);
        dialog.setContentPane(backgroundLabel);
        dialog.setVisible(true);
    }
    
    
    private static void showOrderDialog() {
        //les articles disponibles
        List<Article> articles = articleService.listerArticles();
        if (articles.isEmpty()) {
            JOptionPane.showMessageDialog(frame,
                "Aucun article disponible pour le moment.",
                "Commander",
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JDialog mainDialog = new JDialog(frame, "Passer une commande", true);
        mainDialog.setSize(900, 750);
        mainDialog.setLocationRelativeTo(frame);
        mainDialog.setLayout(new BorderLayout(15, 15));
        mainDialog.getContentPane().setBackground(new Color(245, 245, 245));
       
        // les articles par catégorie
        Map<String, List<Article>> articlesByCategory = new LinkedHashMap<>();
        for (Article article : articles) {
            articlesByCategory.computeIfAbsent(article.getCategorie(), k -> new ArrayList<>()).add(article);
        }
       
        //les onglets par catégorie
        JTabbedPane categoryTabs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        categoryTabs.setFont(new Font("Poppins", Font.BOLD, 14));
        categoryTabs.setBackground(new Color(245, 245, 245));
        categoryTabs.setForeground(new Color(70, 70, 70));
       
        UIManager.put("TabbedPane.selected", new Color(100, 150, 200));
        SwingUtilities.updateComponentTreeUI(categoryTabs);

        Map<Integer, JCheckBox> checkBoxes = new HashMap<>();
        Map<Integer, JSpinner> quantitySpinners = new HashMap<>();

        for (Map.Entry<String, List<Article>> entry : articlesByCategory.entrySet()) {
            JPanel categoryPanel = new JPanel();
            categoryPanel.setLayout(new BoxLayout(categoryPanel, BoxLayout.Y_AXIS));
            categoryPanel.setBackground(Color.WHITE);
            categoryPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

            for (Article article : entry.getValue()) {
                JPanel articleCard = createArticleCard(article, checkBoxes, quantitySpinners);
                categoryPanel.add(articleCard);
                categoryPanel.add(Box.createRigidArea(new Dimension(0, 20)));
            }

            JScrollPane scrollPane = new JScrollPane(categoryPanel);
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            categoryTabs.addTab(entry.getKey(), scrollPane);
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.setBorder(new EmptyBorder(15, 0, 25, 0));

        JButton cancelButton = createStyledButton("Annuler", new Color(120, 120, 120), 160, 45);
        JButton confirmButton = createStyledButton("Confirmer la commande", new Color(100, 150, 200), 220, 45);

        // gestion des événements
        confirmButton.addActionListener(e -> processOrder(mainDialog, articles, checkBoxes, quantitySpinners));
        cancelButton.addActionListener(e -> mainDialog.dispose());

        buttonPanel.add(cancelButton);
        buttonPanel.add(confirmButton);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(245, 245, 245));
        mainPanel.add(categoryTabs, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainDialog.add(mainPanel);
        mainDialog.setVisible(true);
    }

    private static JPanel createArticleCard(Article article, Map<Integer, JCheckBox> checkBoxes,
                                          Map<Integer, JSpinner> quantitySpinners) {
        JPanel card = new JPanel(new BorderLayout(20, 0));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder());
        card.setMaximumSize(new Dimension(Short.MAX_VALUE, 180));

        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(Color.WHITE);
        imagePanel.setPreferredSize(new Dimension(140, 140));
       
        JLabel imageLabel = new JLabel();
        try {
            Image img = loadArticleImage(article);
            imageLabel.setIcon(new ImageIcon(img.getScaledInstance(140, 140, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            imageLabel.setIcon(new ImageIcon(new ImageIcon("default.png").getImage()
                               .getScaledInstance(140, 140, Image.SCALE_SMOOTH)));
        }
        imagePanel.add(imageLabel);
       
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(Color.WHITE);
       
        JLabel nameLabel = new JLabel(article.getNom());
        nameLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        nameLabel.setForeground(new Color(50, 50, 50));
       
        JLabel descLabel = new JLabel("<html><div style='width:300px'>" +
                                    (article.getDescription() != null ? article.getDescription() : "") +
                                    "</div></html>");
        descLabel.setFont(new Font("Poppins", Font.PLAIN, 13));
        descLabel.setForeground(new Color(100, 100, 100));
       
        JLabel priceLabel = new JLabel(String.format("%.2f DT", article.getPrix()));
        priceLabel.setFont(new Font("Poppins", Font.BOLD, 15));
        priceLabel.setForeground(new Color(100, 150, 200));
       
        detailsPanel.add(nameLabel);
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        detailsPanel.add(descLabel);
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        detailsPanel.add(priceLabel);
       
        JPanel selectionPanel = new JPanel();
        selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.Y_AXIS));
        selectionPanel.setBackground(Color.WHITE);
        selectionPanel.setBorder(new EmptyBorder(0, 20, 0, 0));
       
        JCheckBox selectBox = new JCheckBox("Sélectionner");
        selectBox.setFont(new Font("Poppins", Font.PLAIN, 14));
        selectBox.setBackground(Color.WHITE);
        checkBoxes.put(article.getId(), selectBox);
       
        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        quantityPanel.setBackground(Color.WHITE);
       
        JLabel quantityLabel = new JLabel("Quantité:");
        quantityLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
       
        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));
        quantitySpinner.setPreferredSize(new Dimension(80, 30));
        ((JSpinner.DefaultEditor)quantitySpinner.getEditor()).getTextField().setFont(new Font("Poppins", Font.PLAIN, 14));
        quantitySpinners.put(article.getId(), quantitySpinner);
       
        quantityPanel.add(quantityLabel);
        quantityPanel.add(quantitySpinner);
       
        selectionPanel.add(selectBox);
        selectionPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        selectionPanel.add(quantityPanel);
       
        card.add(imagePanel, BorderLayout.WEST);
        card.add(detailsPanel, BorderLayout.CENTER);
        card.add(selectionPanel, BorderLayout.EAST);
       
        return card;
    }

    private static Image loadArticleImage(Article article) throws IOException {
        if (article.getImageUrl() == null || article.getImageUrl().isEmpty()) {
            throw new IOException("Aucune image");
        }
        return article.getImageUrl().startsWith("http")
            ? ImageIO.read(new URL(article.getImageUrl()))
            : ImageIO.read(new File(article.getImageUrl()));
    }

    private static JButton createStyledButton(String text, Color bgColor, int width, int height) {
        JButton button = new JButton(text);
        button.setFont(new Font("Poppins", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(width, height));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 25, 5, 25));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
       
        return button;
    }

    private static void processOrder(JDialog parent, List<Article> articles,
                                   Map<Integer, JCheckBox> checkBoxes,
                                   Map<Integer, JSpinner> quantitySpinners) {
        Map<Integer, Integer> selectedArticles = new HashMap<>();
       
        for (Map.Entry<Integer, JCheckBox> entry : checkBoxes.entrySet()) {
            if (entry.getValue().isSelected()) {
                int id = entry.getKey();
                int quantity = (int) quantitySpinners.get(id).getValue();
                selectedArticles.put(id, quantity);
            }
        }
       
        if (selectedArticles.isEmpty()) {
            JOptionPane.showMessageDialog(parent,
                "Veuillez sélectionner au moins un article",
                "Erreur",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
       
        showCartDialog(parent, articles, selectedArticles);
    }

    private static void showCartDialog(JDialog parent, List<Article> articles,
                                     Map<Integer, Integer> selectedArticles) {
        JDialog cartDialog = new JDialog(parent, "Panier", true);
        cartDialog.setSize(650, 550);
        cartDialog.setLocationRelativeTo(parent);
        cartDialog.setLayout(new BorderLayout(15, 15));
        cartDialog.getContentPane().setBackground(new Color(245, 245, 245));
       
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(245, 245, 245));
        headerPanel.setBorder(new EmptyBorder(15, 20, 15, 20));
       
        JLabel titleLabel = new JLabel("Récapitulatif de commande");
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 18));
        titleLabel.setForeground(new Color(70, 70, 70));
       
        JLabel totalLabel = new JLabel("Total: 0.00 DT");
        totalLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
       
        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(totalLabel, BorderLayout.EAST);
       
        String[] columnNames = {"Article", "Quantité", "Prix unitaire", "Sous-total"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
       
        JTable table = new JTable(model);
        table.setRowHeight(40);
        table.setFont(new Font("Poppins", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("Poppins", Font.BOLD, 14));
        table.setSelectionBackground(new Color(220, 235, 247));
       
        // calcul du total
        Runnable updateTotal = () -> {
            double total = 0;
            for (int i = 0; i < model.getRowCount(); i++) {
                String subTotalStr = (String) model.getValueAt(i, 3);
                total += Double.parseDouble(subTotalStr.replace(" DT", "").replace(",", "."));
            }
            totalLabel.setText(String.format("Total: %.2f DT", total));
        };
       
        for (Map.Entry<Integer, Integer> entry : selectedArticles.entrySet()) {
            Article article = articles.stream()
                .filter(a -> a.getId() == entry.getKey())
                .findFirst()
                .orElse(null);
           
            if (article != null) {
                double subTotal = entry.getValue() * article.getPrix();
                model.addRow(new Object[]{
                    article.getNom(),
                    entry.getValue(),
                    String.format("%.2f DT", article.getPrix()),
                    String.format("%.2f DT", subTotal)
                });
            }
        }
        updateTotal.run();
       
        JButton removeButton = createStyledButton("Retirer", new Color(120, 120, 120), 120, 40);
        JButton validateButton = createStyledButton("Valider la commande", new Color(100, 150, 200), 200, 40);
        JButton backButton = createStyledButton("Retour", new Color(120, 120, 120), 120, 40);
       
        removeButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                model.removeRow(selectedRow);
                updateTotal.run();
            } else {
                JOptionPane.showMessageDialog(cartDialog,
                    "Veuillez sélectionner un article à retirer",
                    "Information",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
       
        validateButton.addActionListener(e -> validateOrder(cartDialog, model, articles));
        backButton.addActionListener(e -> cartDialog.dispose());
       
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.setBorder(new EmptyBorder(10, 0, 20, 0));
        buttonPanel.add(removeButton);
        buttonPanel.add(validateButton);
        buttonPanel.add(backButton);
       
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(245, 245, 245));
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
       
        cartDialog.add(mainPanel);
        cartDialog.setVisible(true);
    }

    private static void validateOrder(JDialog cartDialog, DefaultTableModel model, List<Article> articles) {
        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(cartDialog,
                "Le panier est vide",
                "Erreur",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
       
        Map<Integer, Integer> finalSelection = new HashMap<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            String articleName = (String) model.getValueAt(i, 0);
            int quantity = (int) model.getValueAt(i, 1);
           
            Article article = articles.stream()
                .filter(a -> a.getNom().equals(articleName))
                .findFirst()
                .orElse(null);
           
            if (article != null) {
                finalSelection.put(article.getId(), quantity);
            }
        }
       
        //type de commande
        String[] options = {"Sur place", "À livrer à domicile", "À importer"};
        String orderType = (String) JOptionPane.showInputDialog(
            cartDialog,
            "Sélectionnez le type de commande:",
            "Type de commande",
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);
       
        if (orderType == null) {
            return;
        }
       
        // création de la commande
        Commande command = new Commande(0, clientConnecte.getLogin(),
                                      LocalDateTime.now(), finalSelection, orderType);
       
        if (commandeService.creerCommande(command)) {
            JOptionPane.showMessageDialog(cartDialog,
                "<html><div style='text-align:center;width:250px'>" +
                "Commande passée avec succès!<br>Numéro: " + command.getId() +
                "</div></html>",
                "Succès",
                JOptionPane.INFORMATION_MESSAGE);
            cartDialog.dispose();
            cartDialog.getOwner().dispose();
        } else {
            JOptionPane.showMessageDialog(cartDialog,
                "Erreur lors de la commande",
                "Erreur",
                JOptionPane.ERROR_MESSAGE);
        }
    }



 
    
    
    private static void showClientOrdersDialog() {
        if (clientConnecte == null) {
            JOptionPane.showMessageDialog(frame, "Vous devez être connecté pour voir vos commandes.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
       
        List<Commande> commandes = commandeService.listerCommandesParClient(clientConnecte.getLogin());
        if (commandes.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Vous n'avez aucune commande.", "Mes commandes", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog(frame, "Mes commandes", true);
        dialog.setSize(1100, 700); 
        dialog.setLocationRelativeTo(frame);
        dialog.setLayout(new BorderLayout());
        dialog.getContentPane().setBackground(LIGHT_COLOR);

        String[] columnNames = {"ID", "Date", "Statut", "Type", "Total", "Récupérée"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 5) return Boolean.class;
                return super.getColumnClass(columnIndex);
            }
        };
       
        JTable table = new JTable(model);
        table.setFont(new Font("Poppins", Font.PLAIN, 14));
        table.setRowHeight(35);
        table.setGridColor(new Color(200, 200, 200));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            if (i != 1 && i != 2) { 
                table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }
       
        Map<Integer, Article> articles = commandeService.getArticlesMap();
        for (Commande commande : commandes) {
            double total = commande.calculerTotal(articles);
            model.addRow(new Object[]{
                commande.getId(),
                commande.getDateHeure(),
                commande.getStatut(),
                commande.getType(),
                String.format("%.2f DT", total),
                commande.isRecuperee()
            });
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(PRIMARY_COLOR, 2),
            "Liste de vos commandes",
            javax.swing.border.TitledBorder.LEFT,
            javax.swing.border.TitledBorder.TOP,
            new Font("Poppins", Font.BOLD, 18),
            PRIMARY_COLOR));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        buttonPanel.setBackground(LIGHT_COLOR);
       
        JButton detailsButton = createStyledButton("  Détails", PRIMARY_COLOR, detailIcon);
        detailsButton.setFont(new Font("Poppins", Font.BOLD, 16));
        detailsButton.setPreferredSize(new Dimension(150, 40));
       
        JButton deleteButton = createStyledButton("  Supprimer/Annuler", ACCENT_COLOR, deleteIcon);
        deleteButton.setFont(new Font("Poppins", Font.BOLD, 16));
        deleteButton.setPreferredSize(new Dimension(200, 40));
       
        JButton refreshButton = createStyledButton("  Actualiser", PRIMARY_COLOR, refreshIcon);
        refreshButton.setFont(new Font("Poppins", Font.BOLD, 16));
        refreshButton.setPreferredSize(new Dimension(150, 40));
       
        JButton closeButton = createStyledButton("  Fermer", DARK_COLOR, null);
        closeButton.setFont(new Font("Poppins", Font.BOLD, 16));
        closeButton.setPreferredSize(new Dimension(150, 40));
        
        JButton modifierButton = createStyledButton("  Modifier", PRIMARY_COLOR, editIcon); // Assurez-vous d’avoir une icône 'editIcon'
        modifierButton.setFont(new Font("Poppins", Font.BOLD, 16));
        modifierButton.setPreferredSize(new Dimension(150, 40));


        detailsButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int id = (int) model.getValueAt(selectedRow, 0);
                showCommandeDetails(id);
            } else {
                JOptionPane.showMessageDialog(dialog,
                    "Veuillez sélectionner une commande",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int id = (int) model.getValueAt(selectedRow, 0);
                Commande commande = commandeService.trouverCommandeParId(id);
               
                if (commande == null) {
                    JOptionPane.showMessageDialog(dialog,
                        "Commande introuvable",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
               
                String statut = commande.getStatut();
                boolean estRecuperee = commande.isRecuperee();
               
                if ("Non traitée".equals(statut)) {
                    int confirm = JOptionPane.showConfirmDialog(dialog,
                        "Voulez-vous vraiment annuler cette commande ?\nID: " + id,
                        "Confirmation d'annulation",
                        JOptionPane.YES_NO_OPTION);
                   
                    if (confirm == JOptionPane.YES_OPTION) {
                        if (commandeService.modifierStatutCommande(id, "Annulée")) {
                            model.setValueAt("Annulée", selectedRow, 2);
                            JOptionPane.showMessageDialog(dialog,
                                "Commande annulée avec succès",
                                "Succès",
                                JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(dialog,
                                "Erreur lors de l'annulation de la commande",
                                "Erreur",
                                JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                else if (estRecuperee) {
                    int confirm = JOptionPane.showConfirmDialog(dialog,
                        "Voulez-vous supprimer définitivement cette commande de votre historique ?\nID: " + id,
                        "Confirmation de suppression",
                        JOptionPane.YES_NO_OPTION);
                   
                    if (confirm == JOptionPane.YES_OPTION) {
                        if (commandeService.supprimerCommande(id)) {
                            model.removeRow(selectedRow);
                            JOptionPane.showMessageDialog(dialog,
                                "Commande supprimée de l'historique",
                                "Succès",
                                JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(dialog,
                                "Erreur lors de la suppression de la commande",
                                "Erreur",
                                JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                else {
                    String message = "Opération impossible sur cette commande.\n\n";
                    if ("Annulée".equals(statut)) {
                        message += "Cette commande a déjà été annulée.";
                    } else if ("En préparation".equals(statut) || "Prête".equals(statut)) {
                        message += "Cette commande est en cours de traitement.\n";
                        message += "Seules les commandes 'Non traitée' peuvent être annulées.";
                    } else if ("Livrée".equals(statut) && !estRecuperee) {
                        message += "Cette commande doit d'abord être marquée comme récupérée\n";
                        message += "avant de pouvoir être supprimée de l'historique.";
                    }
                   
                    JOptionPane.showMessageDialog(dialog,
                        message,
                        "Opération impossible",
                        JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(dialog,
                    "Veuillez sélectionner une commande",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        refreshButton.addActionListener(e -> {
            model.setRowCount(0);
            List<Commande> updatedCommandes = commandeService.listerCommandesParClient(clientConnecte.getLogin());
            for (Commande commande : updatedCommandes) {
                double total = commande.calculerTotal(articles);
                model.addRow(new Object[]{
                    commande.getId(),
                    commande.getDateHeure(),
                    commande.getStatut(),
                    commande.getType(),
                    String.format("%.2f DT", total),
                    commande.isRecuperee()
                });
            }
        });

        closeButton.addActionListener(e -> dialog.dispose());
        
        modifierButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int id = (int) model.getValueAt(selectedRow, 0);
                Commande commande = commandeService.trouverCommandeParId(id);

                if (commande == null) {
                    JOptionPane.showMessageDialog(dialog,
                        "Commande introuvable",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String statut = commande.getStatut();
                if (!"Non traitée".equals(statut)) {
                    JOptionPane.showMessageDialog(dialog,
                        "Seules les commandes 'Non traitée' peuvent être modifiées.",
                        "Modification impossible",
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }

                showCommandeModificationDialog(dialog, commande, model, selectedRow);
            } else {
                JOptionPane.showMessageDialog(dialog,
                    "Veuillez sélectionner une commande",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            }
        });


        buttonPanel.add(detailsButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(closeButton);
        buttonPanel.add(modifierButton);

        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
    
    
    private static void showCommandeModificationDialog(JDialog parent, Commande commande, DefaultTableModel model, int rowIndex) {
        String[] typesPossibles = {"Sur place", "À livrer à domicile", "À importer"};
        JComboBox<String> typeCombo = new JComboBox<>(typesPossibles);
        typeCombo.setSelectedItem(commande.getType());

        List<Article> articlesDisponibles = articleService.listerArticles();

        Map<String, Article> nomToArticleMap = new HashMap<>();
        for (Article a : articlesDisponibles) {
            nomToArticleMap.put(a.getNom(), a);
        }

        JPanel articlesPanel = new JPanel();
        articlesPanel.setLayout(new BoxLayout(articlesPanel, BoxLayout.Y_AXIS));
        articlesPanel.setBorder(BorderFactory.createTitledBorder("Articles et Quantités"));

        List<JComboBox<String>> comboBoxes = new ArrayList<>();
        List<JTextField> quantiteFields = new ArrayList<>();

        Map<Integer, Integer> articlesQuantites = new HashMap<>(commande.getArticlesQuantites());

        for (Map.Entry<Integer, Integer> entry : articlesQuantites.entrySet()) {
            JPanel line = new JPanel(new FlowLayout(FlowLayout.LEFT));

            String nomArticle = articlesDisponibles.stream()
                .filter(a -> a.getId() == entry.getKey())
                .map(Article::getNom)
                .findFirst()
                .orElse("Article inconnu");

            JComboBox<String> combo = new JComboBox<>(nomToArticleMap.keySet().toArray(new String[0]));
            combo.setSelectedItem(nomArticle);
            comboBoxes.add(combo);

            JTextField quantiteField = new JTextField(String.valueOf(entry.getValue()), 3);
            quantiteFields.add(quantiteField);

            line.add(combo);
            line.add(new JLabel("Quantité :"));
            line.add(quantiteField);

            JButton retirerBtn = new JButton("Retirer");
            retirerBtn.addActionListener(e -> {
                articlesPanel.remove(line);
                articlesPanel.revalidate();
                articlesPanel.repaint();

                comboBoxes.remove(combo);
                quantiteFields.remove(quantiteField);
            });
            line.add(retirerBtn);

            articlesPanel.add(line);
        }

        JPanel addArticlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JComboBox<String> newArticleCombo = new JComboBox<>(nomToArticleMap.keySet().toArray(new String[0]));
        JTextField newQuantiteField = new JTextField(3);
        JButton ajouterBtn = new JButton("Ajouter");

        addArticlePanel.add(new JLabel("Nouvel article :"));
        addArticlePanel.add(newArticleCombo);
        addArticlePanel.add(new JLabel("Quantité :"));
        addArticlePanel.add(newQuantiteField);
        addArticlePanel.add(ajouterBtn);

        ajouterBtn.addActionListener(e -> {
            String nomSelected = (String) newArticleCombo.getSelectedItem();
            if (nomSelected == null || newQuantiteField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(parent, "Veuillez sélectionner un article et saisir la quantité.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                int qte = Integer.parseInt(newQuantiteField.getText().trim());
                if (qte <= 0) {
                    JOptionPane.showMessageDialog(parent, "La quantité doit être positive.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                JPanel line = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JComboBox<String> combo = new JComboBox<>(nomToArticleMap.keySet().toArray(new String[0]));
                combo.setSelectedItem(nomSelected);
                comboBoxes.add(combo);

                JTextField quantiteField = new JTextField(String.valueOf(qte), 3);
                quantiteFields.add(quantiteField);

                line.add(combo);
                line.add(new JLabel("Quantité :"));
                line.add(quantiteField);

                JButton retirerBtn = new JButton("Retirer");
                retirerBtn.addActionListener(ev -> {
                    articlesPanel.remove(line);
                    articlesPanel.revalidate();
                    articlesPanel.repaint();

                    comboBoxes.remove(combo);
                    quantiteFields.remove(quantiteField);
                });
                line.add(retirerBtn);

                articlesPanel.add(line);
                articlesPanel.revalidate();
                articlesPanel.repaint();

                newQuantiteField.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(parent, "Quantité invalide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Type de commande :"));
        topPanel.add(typeCombo);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(articlesPanel), BorderLayout.CENTER);
        mainPanel.add(addArticlePanel, BorderLayout.SOUTH);
        mainPanel.setPreferredSize(new Dimension(500, 400));

        int result = JOptionPane.showConfirmDialog(parent, mainPanel, "Modifier Commande",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String nouveauType = (String) typeCombo.getSelectedItem();

            Map<Integer, Integer> nouvellesArticlesQuantites = new HashMap<>();

            for (int i = 0; i < comboBoxes.size(); i++) {
                String nom = (String) comboBoxes.get(i).getSelectedItem();
                Article art = nomToArticleMap.get(nom);
                if (art == null) continue;

                try {
                    int qte = Integer.parseInt(quantiteFields.get(i).getText().trim());
                    if (qte > 0) {
                        nouvellesArticlesQuantites.put(art.getId(), qte);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(parent, "Quantité invalide pour l'article " + nom, "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            commande.setType(nouveauType);
            commande.setArticlesQuantites(nouvellesArticlesQuantites);

            boolean succes = commandeService.mettreAJourCommande(commande);
            if (succes) {
                model.setValueAt(nouveauType, rowIndex, 3); // colonne type
                model.setValueAt(commande.getArticlesQuantites().toString(), rowIndex, 5); // colonne articles

                double nouveauTotal = commande.calculerTotal(nomToArticleMap.values().stream()
                    .collect(Collectors.toMap(Article::getId, a -> a))); // conversion List<Article> en Map<Integer, Article>

                model.setValueAt(nouveauTotal, rowIndex, 4); // colonne total (vérifie bien que c'est la 4)

                JOptionPane.showMessageDialog(parent, "Commande modifiée avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    } 


    private static void showEditClientInfoDialog() {
        if (clientConnecte == null) {
            JOptionPane.showMessageDialog(frame, "Vous devez être connecté pour modifier vos informations.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Client client = clientService.trouverClientParLogin(clientConnecte.getLogin());
        if (client == null) {
            JOptionPane.showMessageDialog(frame, "Client non trouvé", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog(frame, "Modifier mes informations", true);
        dialog.setSize(600, 500);
        dialog.setLocationRelativeTo(frame);
        dialog.setLayout(new BorderLayout());
        dialog.getContentPane().setBackground(LIGHT_COLOR);

        JPanel formPanel = new JPanel(new GridLayout(7, 2, 15, 15));
        formPanel.setBackground(LIGHT_COLOR);
        formPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        JLabel loginLabel = new JLabel("Login:");
        loginLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        JTextField loginField = new JTextField(client.getLogin());
        loginField.setFont(new Font("Poppins", Font.PLAIN, 16));
        loginField.setEditable(false);
        
        JLabel passwordLabel = new JLabel("Nouveau mot de passe:");
        passwordLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Poppins", Font.PLAIN, 16));
        
        JLabel nomLabel = new JLabel("Nom:");
        nomLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        JTextField nomField = new JTextField(client.getNom());
        nomField.setFont(new Font("Poppins", Font.PLAIN, 16));
        
        JLabel prenomLabel = new JLabel("Prénom:");
        prenomLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        JTextField prenomField = new JTextField(client.getPrenom());
        prenomField.setFont(new Font("Poppins", Font.PLAIN, 16));
        
        JLabel dateLabel = new JLabel("Date naissance (AAAA-MM-JJ):");
        dateLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        JTextField dateField = new JTextField(client.getDateNaissance().toString());
        dateField.setFont(new Font("Poppins", Font.PLAIN, 16));
        
        JLabel adresseLabel = new JLabel("Adresse:");
        adresseLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        JTextField adresseField = new JTextField(client.getAdresse());
        adresseField.setFont(new Font("Poppins", Font.PLAIN, 16));
        
        JLabel telLabel = new JLabel("Téléphone:");
        telLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        JTextField telField = new JTextField(client.getTelephone());
        telField.setFont(new Font("Poppins", Font.PLAIN, 16));

        formPanel.add(loginLabel);
        formPanel.add(loginField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(nomLabel);
        formPanel.add(nomField);
        formPanel.add(prenomLabel);
        formPanel.add(prenomField);
        formPanel.add(dateLabel);
        formPanel.add(dateField);
        formPanel.add(adresseLabel);
        formPanel.add(adresseField);
        formPanel.add(telLabel);
        formPanel.add(telField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        buttonPanel.setBackground(LIGHT_COLOR);
        JButton saveButton = createStyledButton("Enregistrer", PRIMARY_COLOR, null);
        saveButton.setFont(new Font("Poppins", Font.BOLD, 16));
        JButton cancelButton = createStyledButton("Annuler", DARK_COLOR, null);
        cancelButton.setFont(new Font("Poppins", Font.BOLD, 16));



        saveButton.addActionListener(e -> {
            try {
                String newPassword = new String(passwordField.getPassword());
                if (!newPassword.isEmpty()) {
                    client.setPassword(newPassword);
                }
                
                client.setNom(nomField.getText());
                client.setPrenom(prenomField.getText());
                client.setDateNaissance(LocalDate.parse(dateField.getText()));
                client.setAdresse(adresseField.getText());
                client.setTelephone(telField.getText());

                if (clientService.modifierClient(client)) {
                    clientConnecte = client;
                    JOptionPane.showMessageDialog(dialog, "Informations modifiées avec succès");
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Erreur lors de la modification", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Données invalides", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private static JPanel createStatisticsPanel(JPanel contentPanel) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(LIGHT_COLOR);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.setBackground(LIGHT_COLOR);
        tabbedPane.setForeground(PRIMARY_COLOR);
        tabbedPane.setFont(new Font("Poppins", Font.BOLD, 16));

        JPanel generalStatsPanel = createGeneralStatsPanel();
        tabbedPane.addTab("Général", statsGeneralIcon, generalStatsPanel, "Statistiques générales");

        JPanel ventesPanel = createSalesStatsPanel();
        tabbedPane.addTab("Ventes", statsSalesIcon, ventesPanel, "Statistiques de ventes");

        JPanel clientsFidelesPanel = createLoyalClientsPanel();
        tabbedPane.addTab("Clients fidèles", statsClientsIcon, clientsFidelesPanel, "Clients fidèles");

        JPanel articlesPanel = createPopularItemsPanel();
        tabbedPane.addTab("Articles populaires", statsPopularIcon, articlesPanel, "Articles les plus populaires");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        buttonPanel.setBackground(LIGHT_COLOR);
        JButton backButton = createStyledButton("Retour", DARK_COLOR, homeIcon);
        backButton.setFont(new Font("Poppins", Font.BOLD, 16));
        backButton.addActionListener(e -> {
            CardLayout cl = (CardLayout)(contentPanel.getLayout());
            cl.show(contentPanel, "MENU_GERANT");
        });
        buttonPanel.add(backButton);

        panel.add(tabbedPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private static JPanel createGeneralStatsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(LIGHT_COLOR);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setBackground(LIGHT_COLOR);
        
        JLabel titleLabel = new JLabel("Statistiques Générales");
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 24));
        titleLabel.setForeground(PRIMARY_COLOR);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        statsPanel.add(titleLabel);
        statsPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel cardsPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        cardsPanel.setBackground(LIGHT_COLOR);
        
        cardsPanel.add(createStatCard("Nombre total de commandes", 
                                    String.valueOf(commandeService.getTotalOrders()), 
                                    orderIcon));
        
        cardsPanel.add(createStatCard("Montant total des ventes", 
                                    String.format("%.2f DT", commandeService.getTotalSales()), 
                                    salesIcon));
        
        cardsPanel.add(createStatCard("Nombre total de clients", 
                                    String.valueOf(clientService.getTotalClients()), 
                                    clientIcon));
        
        cardsPanel.add(createStatCard("Commandes récupérées/non récupérées", 
                                    commandeService.getNombreCommandesRecuperees() + "/" + commandeService.getNombreCommandesNonRecuperees(), addIcon), 
                                    statusIcon);

        statsPanel.add(cardsPanel);
        statsPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        Map<String, Integer> commandesParStatut = commandeService.getCommandesParStatut();
        String[] statutColumns = {"Statut", "Nombre de commandes"};
        DefaultTableModel statutModel = new DefaultTableModel(statutColumns, 0);
        
        for (Map.Entry<String, Integer> entry : commandesParStatut.entrySet()) {
            statutModel.addRow(new Object[]{entry.getKey(), entry.getValue()});
        }
        
        JTable statutTable = new JTable(statutModel);
        statutTable.setFont(new Font("Poppins", Font.PLAIN, 14));
        statutTable.setRowHeight(35);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        statutTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        statutTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        
        JScrollPane statutScroll = new JScrollPane(statutTable);
        statutScroll.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(PRIMARY_COLOR, 2), 
            "Répartition des commandes par statut", 
            javax.swing.border.TitledBorder.LEFT, 
            javax.swing.border.TitledBorder.TOP,
            new Font("Poppins", Font.BOLD, 18),
            PRIMARY_COLOR));
        
        panel.add(statsPanel, BorderLayout.NORTH);
        panel.add(statutScroll, BorderLayout.CENTER);

        return panel;
    }

    private static JPanel createStatCard(String title, String value, Icon icon) {
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(PRIMARY_COLOR, 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(iconLabel, BorderLayout.WEST);
        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Poppins", Font.BOLD, 20));
        valueLabel.setForeground(PRIMARY_COLOR);
        valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(valueLabel);
        
        card.add(contentPanel, BorderLayout.CENTER);
        
        return card;
    }

    private static JPanel createSalesStatsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(LIGHT_COLOR);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        Map<LocalDate, Double> ventesJournalieres = commandeService.getVentesJournalieres();
        String[] ventesColumns = {"Date", "Montant (DT)"};
        DefaultTableModel ventesModel = new DefaultTableModel(ventesColumns, 0);
        
        for (Map.Entry<LocalDate, Double> entry : ventesJournalieres.entrySet()) {
            ventesModel.addRow(new Object[]{entry.getKey(), String.format("%.2f", entry.getValue())});
        }
        
        JTable ventesTable = new JTable(ventesModel);
        ventesTable.setFont(new Font("Poppins", Font.PLAIN, 14));
        ventesTable.setRowHeight(35);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        ventesTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        ventesTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        
        JScrollPane ventesScroll = new JScrollPane(ventesTable);
        ventesScroll.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(PRIMARY_COLOR, 2), 
            "Ventes journalières", 
            javax.swing.border.TitledBorder.LEFT, 
            javax.swing.border.TitledBorder.TOP,
            new Font("Poppins", Font.BOLD, 18),
            PRIMARY_COLOR));

        Map<String, Double> ventesParCategorie = commandeService.getVentesParCategorie();
        String[] categorieColumns = {"Catégorie", "Montant (DT)"};
        DefaultTableModel categorieModel = new DefaultTableModel(categorieColumns, 0);
        
        for (Map.Entry<String, Double> entry : ventesParCategorie.entrySet()) {
            categorieModel.addRow(new Object[]{entry.getKey(), String.format("%.2f", entry.getValue())});
        }
        
        JTable categorieTable = new JTable(categorieModel);
        categorieTable.setFont(new Font("Poppins", Font.PLAIN, 14));
        categorieTable.setRowHeight(35);
        
        categorieTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        
        JScrollPane categorieScroll = new JScrollPane(categorieTable);
        categorieScroll.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(PRIMARY_COLOR, 2), 
            "Répartition des ventes par catégorie", 
            javax.swing.border.TitledBorder.LEFT, 
            javax.swing.border.TitledBorder.TOP,
            new Font("Poppins", Font.BOLD, 18),
            PRIMARY_COLOR));

        JPanel ventesTablesPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        ventesTablesPanel.setBackground(LIGHT_COLOR);
        ventesTablesPanel.add(ventesScroll);
        ventesTablesPanel.add(categorieScroll);

        panel.add(ventesTablesPanel, BorderLayout.CENTER);

        return panel;
    }

    private static JPanel createLoyalClientsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(LIGHT_COLOR);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        List<Client> clients = clientService.listerClients();
        Map<String, Integer> commandesParClient = new HashMap<>();
        
        for (Client client : clients) {
            List<Commande> commandesClient = commandeService.listerCommandesParClient(client.getLogin());
            long nbCommandesRecuperees = commandesClient.stream()
                .filter(Commande::isRecuperee)
                .count();
            
            if (nbCommandesRecuperees > 0) {
                commandesParClient.put(client.getPrenom() + " " + client.getNom(), (int) nbCommandesRecuperees);
            }
        }
        
        List<Map.Entry<String, Integer>> clientsTries = new ArrayList<>(commandesParClient.entrySet());
        clientsTries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        
        String[] clientsColumns = {"Client", "Nombre de commandes récupérées"};
        DefaultTableModel clientsModel = new DefaultTableModel(clientsColumns, 0);
        
        for (Map.Entry<String, Integer> entry : clientsTries) {
            clientsModel.addRow(new Object[]{entry.getKey(), entry.getValue()});
        }
        
        JTable clientsTable = new JTable(clientsModel);
        clientsTable.setFont(new Font("Poppins", Font.PLAIN, 14));
        clientsTable.setRowHeight(35);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        clientsTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        
        JScrollPane clientsScroll = new JScrollPane(clientsTable);
        clientsScroll.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(PRIMARY_COLOR, 2), 
            "Clients fidèles", 
            javax.swing.border.TitledBorder.LEFT, 
            javax.swing.border.TitledBorder.TOP,
            new Font("Poppins", Font.BOLD, 18),
            PRIMARY_COLOR));

        panel.add(clientsScroll, BorderLayout.CENTER);

        return panel;
    }

    private static JPanel createPopularItemsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(LIGHT_COLOR);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        Map<String, Integer> articlesQuantites = new HashMap<>();
        List<Commande> allCommandes = commandeService.listerCommandes();
        Map<Integer, Article> articlesMap = commandeService.getArticlesMap();
        
        for (Commande commande : allCommandes) {
            for (Map.Entry<Integer, Integer> entry : commande.getArticlesQuantites().entrySet()) {
                Article article = articlesMap.get(entry.getKey());
                if (article != null) {
                    articlesQuantites.merge(article.getNom(), entry.getValue(), Integer::sum);
                }
            }
        }
        
        List<Map.Entry<String, Integer>> articlesTries = new ArrayList<>(articlesQuantites.entrySet());
        articlesTries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        
        if (articlesTries.size() > 10) {
            articlesTries = articlesTries.subList(0, 10);
        }
        
        String[] articlesColumns = {"Article", "Quantité commandée"};
        DefaultTableModel articlesModel = new DefaultTableModel(articlesColumns, 0);
        
        for (Map.Entry<String, Integer> entry : articlesTries) {
            articlesModel.addRow(new Object[]{entry.getKey(), entry.getValue()});
        }
        
        JTable articlesTable = new JTable(articlesModel);
        articlesTable.setFont(new Font("Poppins", Font.PLAIN, 14));
        articlesTable.setRowHeight(35);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        articlesTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        
        JScrollPane articlesScroll = new JScrollPane(articlesTable);
        articlesScroll.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(PRIMARY_COLOR, 2), 
            "Top 10 des articles les plus commandés", 
            javax.swing.border.TitledBorder.LEFT, 
            javax.swing.border.TitledBorder.TOP,
            new Font("Poppins", Font.BOLD, 18),
            PRIMARY_COLOR));

        panel.add(articlesScroll, BorderLayout.CENTER);

        return panel;
    }
    
    private static JButton createStyledButton(String text, Color bgColor, Icon icon) {
        JButton button = new JButton(text, icon);
        button.setBackground(bgColor);
        button.setForeground(WHITE);
        button.setFont(new Font("Poppins", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(bgColor.darker(), 1),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setHorizontalTextPosition(SwingConstants.RIGHT);
        button.setVerticalTextPosition(SwingConstants.CENTER);
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
        
        return button;
    }
}