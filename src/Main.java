import controller.GerenciadorMidia;
import modelo.EnumCategoriaMusicas;
import modelo.Midia;
import modelo.Musica;
import modelo.Pessoa;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
    GerenciadorMidia g = new GerenciadorMidia();

    EnumCategoriaMusicas categoria = EnumCategoriaMusicas.KPOP;
    Pessoa robinho = new Pessoa("Robinho");
    Midia musica = new Musica("A grande Salada",10, "C:\\Users\\PC\\Desktop\\oi.tpoo", 10,robinho , categoria );

    g.abrir(musica.getLocal());


}
