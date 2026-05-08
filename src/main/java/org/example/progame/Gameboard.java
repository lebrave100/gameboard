package org.example.progame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.Random;

public class Gameboard extends Application {
    // 🔹 Grid constants
    private static final int ROWS = 10;
    private static final int COLS = 10;
    // Scene constants
    private static final int SCENE_WIDTH = 800;
    private static final int SCENE_HEIGHT = 800;

    enum CellType {
        GRASS, PLAYER, PRINCESS, BOMB, WALL
    }

    // 🔹 Use "matrix" instead of "map"
    private CellType[][] matrix = new CellType[ROWS][COLS];
    private GridPane grid;
    private int playerRow = 1;
    private int playerCol = 1;

    @Override
    public void start(Stage stage) {

        initMatrix();
        grid = new GridPane();
        drawBoard(grid);

        BorderPane root = new BorderPane();
        root.setCenter(grid);

        Scene scene = new Scene(root, SCENE_WIDTH,SCENE_HEIGHT);

        stage.setTitle("Rescue the Princess");
        stage.setScene(scene);
        stage.show();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case DOWN -> movePlayer(1, 0);
                case RIGHT -> movePlayer(0, 1);
                case LEFT -> movePlayer(0, -1);
                case UP -> movePlayer(-1, 0);
            }
        });
    }


        private void movePlayer(int cph, int cpv) {
            int newRow = playerRow + cph;
            int newCol = playerCol + cpv;

            if (matrix[newRow][newCol] != CellType.WALL)
            {
                matrix[playerRow][playerCol] = CellType.GRASS;
                playerRow = newRow;
                playerCol = newCol;
                matrix[playerRow][playerCol] = CellType.PLAYER;
                drawBoard(grid);
            }
            matrix[playerRow][playerCol] = CellType.PLAYER;
            drawBoard(grid);
        }



    private void initMatrix() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                matrix[r][c] = CellType.GRASS;
            }
        }
        //Perimiter of wall
        for (int r = 0; r < matrix.length; r++) {
            matrix[r][0] = CellType.WALL;
            matrix[r][COLS-1] = CellType.WALL;
        }
        for (int c = 0; c < matrix[0].length; c++) {
            matrix[0][c] = CellType.WALL;
            matrix[ROWS-1][c] = CellType.WALL;
        }
        // Sample objects
        matrix[1][1] = CellType.PLAYER;
        //matrix[6][5] = CellType.PRINCESS;
        //matrix[4][5] = CellType.BOMB;
        Random random = new Random();
        int r, c;
        do
        {
            r = random.nextInt(ROWS); c = random.nextInt(COLS); }
            while (matrix[r][c] != CellType.GRASS);
            matrix[r][c] = CellType.PRINCESS;

        do
        {
            r = random.nextInt(ROWS); c = random.nextInt(COLS);}
            while (matrix[r][c] != CellType.GRASS);
            matrix[r][c] = CellType.BOMB;
    }


    private void drawBoard(GridPane grid) {
        grid.getChildren().clear();

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {

                StackPane cell = new StackPane();
                cell.setPrefSize(SCENE_WIDTH / COLS, SCENE_HEIGHT / ROWS);
                cell.setStyle("-fx-border-color: black; -fx-background-color: beige;");

                Label label = new Label();


                if(matrix[row][col] == CellType.PLAYER ) {
                    //label.setText("🧍");
                    ImageView imageView = new ImageView(new Image("/player.png"));
                    imageView.setFitWidth(SCENE_WIDTH / COLS);
                    imageView.setFitHeight(SCENE_HEIGHT / ROWS);
                    cell.getChildren().add(imageView);
                }else if(matrix[row][col] == CellType.PRINCESS ) {
                    //label.setText("👸");
                    ImageView imageView = new ImageView(new Image("/princess.png"));
                    imageView.setFitWidth(SCENE_WIDTH / COLS);
                    imageView.setFitHeight(SCENE_HEIGHT / ROWS);
                    cell.getChildren().add(imageView);
                    //=============================================================
                }else if(matrix[row][col] == CellType.BOMB){
                    //label.setText("💣");
                    ImageView imageView = new ImageView(new Image("/bomb.png"));
                    imageView.setFitWidth(SCENE_WIDTH / COLS);
                    imageView.setFitHeight(SCENE_HEIGHT / ROWS);
                    cell.getChildren().add(imageView);
                    //=============================================================
                }else if(matrix[row][col] == CellType.WALL){
                    //label.setText("");
                    ImageView imageView = new ImageView(new Image("/wall.png"));
                    imageView.setFitWidth(SCENE_WIDTH / COLS);
                    imageView.setFitHeight(SCENE_HEIGHT / ROWS);
                    cell.getChildren().add(imageView);
                    cell.setStyle("-fx-border-color: black; -fx-background-color: gray;");
                    //==================================================================
                }else{
                    //label.setText("");
                    ImageView imageView = new ImageView(new Image("/grass.png"));
                    imageView.setFitWidth(SCENE_WIDTH / COLS);
                    imageView.setFitHeight(SCENE_HEIGHT / ROWS);
                    cell.getChildren().add(imageView);
                }

                cell.getChildren().add(label);
                grid.add(cell, col, row);
            }
        }
    }


}