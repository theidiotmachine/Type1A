package typeIa;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.*;
import de.lessvoid.nifty.controls.ListBox;
import de.lessvoid.nifty.controls.listbox.builder.ListBoxBuilder;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.examples.LwjglInitHelper;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.spi.time.TimeProvider;

import java.io.IOException;
import java.util.Random;

/**
 * Created by tim on 09/05/15.
 */
public class Whoops {

    /**
     * The "model" class. This should really be your own class.
     */
    public static class TableRow {
        public String[] data = new String[5];

        public TableRow(final String ... param) {
            for (int i=0; i<param.length; i++) {
                data[i] = param[i];
            }
        }
    }

    /**
     * The ViewConverter class that connects the model class (TableRow) to the nifty world.
     */
    public static class TableRowViewConverter implements ListBox.ListBoxViewConverter<TableRow> {
        @Override
        public void display(final Element listBoxItem, final TableRow item) {
            for (int i=0; i<5; i++) {
                // get the text element for the row
                Element textElement = listBoxItem.findElementByName("#col-" + String.valueOf(i));
                textElement.getRenderer(TextRenderer.class).setText(item.data[i]);
            }
        }

        @Override
        public int getWidth(final Element listBoxItem, final TableRow item) {
            int width = 0;
            for (int i=0; i<5; i++) {
                TextRenderer renderer = listBoxItem.findElementByName("#col-" + String.valueOf(i)).getRenderer(TextRenderer.class);
                width += renderer.getFont().getWidth(item.data[i]);
            }
            return width;
        }
    }

    public static void main(final String[] args) throws IOException {
        if (!LwjglInitHelper.initSubSystems("Nifty Hello World")) {
            System.exit(0);
        }

        // create nifty
        Nifty nifty = null;/*new Nifty(
                new LwjglRenderDevice(),
                new OpenALSoundDevice(),
                LwjglInitHelper.getInputSystem(),
                new TimeProvider());
*/
        nifty.loadControlFile("nifty-default-controls.xml");
        nifty.loadStyleFile("nifty-default-styles.xml");
/*
ControlDefinitionBuilder rowControlBuilder = new ControlDefinitionBuilder("row") {{
...
        visibleToMouse();
        controller("de.lessvoid.nifty.controls.listbox.ListBoxItemController");
        inputMapping("de.lessvoid.nifty.input.mapping.MenuInputMapping");
        onHoverEffect(new HoverEffectBuilder("colorBar") {{
          effectParameter("color", "#880f");
          post(true);
          inset("1px");
          neverStopRendering(true);
          effectParameter("timeType", "infinite");
        }});
        onCustomEffect(new EffectBuilder("colorBar") {{
          customKey("focus");
          post(false);
          effectParameter("color", "#f00f");
          neverStopRendering(true);
          effectParameter("timeType", "infinite");
        }});
        onCustomEffect(new EffectBuilder("colorBar") {{
          customKey("select");
          post(false);
          effectParameter("color", "#f00f");
          neverStopRendering(true);
          effectParameter("timeType", "infinite");
        }});
        onCustomEffect(new EffectBuilder("textColor") {{
          customKey("select");
          post(false);
          effectParameter("color", "#000f");
          neverStopRendering(true);
          effectParameter("timeType", "infinite");
        }});
        onClickEffect(new EffectBuilder("focus") {{
          effectParameter("targetElement", "#parent#parent");
        }});
        interactOnClick("listBoxItemClicked()");
      }});
 */
        // first step is to register the control that we'll use for the rows
        ControlDefinitionBuilder rowControlBuilder = new ControlDefinitionBuilder("row") {{
            panel(new PanelBuilder() {{
                childLayoutHorizontal();
                width("100%");
                alignCenter();
                text(new TextBuilder("#col-0") {{
                    width("20%");
                    style("base-font");
                }});
                text(new TextBuilder("#col-1") {{
                    width("20%");
                    style("base-font");
                }});
                text(new TextBuilder("#col-2") {{
                    width("20%");
                    style("base-font");
                }});
                text(new TextBuilder("#col-3") {{
                    width("20%");
                    style("base-font");
                }});
                text(new TextBuilder("#col-4") {{
                    width("20%");
                    style("base-font");
                }});
            }});
        }};
        rowControlBuilder.registerControlDefintion(nifty);

        // now build the screen
        ScreenBuilder builder = new ScreenBuilder("start") {{
            layer(new LayerBuilder("layer") {{
                childLayoutCenter();
                backgroundColor("#400f");
                control(new ListBoxBuilder("serverBox") {{
                    viewConverterClass(TableRowViewConverter.class);
                    displayItems(20);
                    selectionModeDisabled();
                    hideHorizontalScrollbar();
                    width("765px");
                    height("500px");
                    childLayoutVertical();
                    optionalVerticalScrollbar();
                    alignCenter();
                    valignCenter();
                    control(new ControlBuilder("row"));
                }});
            }});
        }};
        Screen startScreen = builder.build(nifty);

        // get the list box
        ListBox<TableRow> listBox = startScreen.findNiftyControl("serverBox", ListBox.class);

        // add 1000 rows (instances of our model class TableRow)
        for (int i=0; i<1000; i++) {
            listBox.addItem(new TableRow(randomString(), randomString(), randomString(), randomString(), randomString()));
        }

        // finally start the screen
        nifty.gotoScreen("start");
        //...
    }

    // just a piece of code to get random strings to add to the table
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static Random rnd = new Random();

    private static String randomString() {
        StringBuilder sb = new StringBuilder(5);
        for(int i=0; i<5; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }
}
