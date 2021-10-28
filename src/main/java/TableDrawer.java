import com.inamik.text.tables.GridTable;
import com.inamik.text.tables.SimpleTable;
import com.inamik.text.tables.grid.Border;
import com.inamik.text.tables.grid.Util;

import java.util.List;

import static com.inamik.text.tables.Cell.Functions.HORIZONTAL_CENTER;
import static com.inamik.text.tables.Cell.Functions.VERTICAL_CENTER;

public abstract class TableDrawer {

    public static void drawGameRulesTable(List<String> choices)
    {
        final int borderOfLose = choices.size() / 2;
        SimpleTable table = SimpleTable.of().nextRow();
        table.nextCell("PC / User").applyToCell(VERTICAL_CENTER.withHeight(1))
                .applyToCell(HORIZONTAL_CENTER.withWidth(3));
        for (String choice : choices)
        {
            table.nextCell().addLine(choice);
            table.applyToCell(VERTICAL_CENTER.withHeight(1))
                    .applyToCell(HORIZONTAL_CENTER.withWidth(3));
        }
        for (int i = 0; i < choices.size(); i++)
        {
            table.nextRow();
            table.nextCell().addLine(choices.get(i));
            table.applyToCell(VERTICAL_CENTER.withHeight(1)).applyToCell(HORIZONTAL_CENTER.withWidth(3));
            for (int j = 0; j < choices.size(); j++)
            {
                table.nextCell();
                int diff = j - i;
                if (diff == 0)
                {
                    table.addLine(GameRules.DRAW);
                } else if ((diff < -borderOfLose && i > j) || (diff <= borderOfLose && j > i))
                {
                    table.addLine(GameRules.WIN);
                } else if ((diff >= -borderOfLose && j < i) || (diff > borderOfLose && i < j))
                {
                    table.addLine(GameRules.LOSE);
                }
                table.applyToCell(VERTICAL_CENTER.withHeight(1)).applyToCell(HORIZONTAL_CENTER.withWidth(3));
            }
        }




        GridTable g = table.toGrid();
        g = Border.of(Border.Chars.of('+', '-', '|')).apply(g);
        Util.print(g);
    }
}
