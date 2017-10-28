/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.utilitarios.validatios;

/**
 *
 * @author Fernando
 */
public class Table {

    private int order;
    private String title;

    public Table(int order, String title) {
        this.order = order;
        this.title = title;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int compareTo(Table o) {
        if (this.order < o.getOrder()) {
            return -1;
        }
        if (this.order >= o.getOrder()) {
            return 1;
        }
        return 0;
    }
}
