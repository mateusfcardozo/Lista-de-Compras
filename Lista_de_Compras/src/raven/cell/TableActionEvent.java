
package raven.cell;

public interface TableActionEvent {

    public void onDelete(int row);

    public void onView(int row);
}