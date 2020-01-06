package me.agramon.albedo.commands.fun;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.kodehawa.lib.imageboards.DefaultImageBoards;
import net.kodehawa.lib.imageboards.entities.BoardImage;

import java.awt.*;
import java.util.ArrayList;

public class Albedo extends Command {
    public Albedo() {
        super.name = "albedo";
        super.category = new Category("Fun");
        super.help = "Random image/gif of Albedo";
        super.aliases = new String[]{"waifu"};
        super.cooldown = 5;
    }

    @Override
    protected void execute(CommandEvent e) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Color.MAGENTA);
        eb.setTitle("** Albedo - The Best Waifu **");

        if (e.getArgs().equals("gif")) {
            eb.setImage(getGIF());
        } else {
            BoardImage image = DefaultImageBoards.SAFEBOORU.search("albedo").blocking().get((int)(Math.random() * 60));
            eb.setImage(image.getURL());
        }
        eb.addField("Fun Fact: ", getFact(), false);
        e.reply(eb.build());
    }

    private String getGIF() {
        ArrayList<String> gifs = new ArrayList<>();
        gifs.add("https://thumbs.gfycat.com/FragrantAdoredGalapagosalbatross-size_restricted.gif");
        gifs.add("https://thumbs.gfycat.com/OffbeatPeskyFlatcoatretriever-size_restricted.gif");
        gifs.add("https://thumbs.gfycat.com/ForcefulInsidiousIcterinewarbler-size_restricted.gif");
        gifs.add("https://thumbs.gfycat.com/ZigzagMiserlyIberianmole-size_restricted.gif");
        gifs.add("https://thumbs.gfycat.com/ArcticSnarlingGosling-size_restricted.gif");
        gifs.add("https://thumbs.gfycat.com/EthicalAbandonedHoneybadger-size_restricted.gif");
        gifs.add("https://thumbs.gfycat.com/VagueWellinformedArmyworm-size_restricted.gif");
        gifs.add("https://thumbs.gfycat.com/GraciousSilentEastrussiancoursinghounds-size_restricted.gif");

        return gifs.get((int) (Math.random() * gifs.size()));
    }

    private String getFact() {
        ArrayList<String> facts = new ArrayList<>();
        facts.add("In the Web Novel, Albedo doesn't exist, along with her sisters.");
        facts.add("Albedo is a Latin word meaning \"whiteness.\"");
        facts.add("Albedo is the second NPC to be given the Ring of Ainz Ooal Gown.");
        facts.add("While Albedo is limited to only one set of clothes, she possesses several different kinds of underwear.");
        facts.add("In the Manga, Albedo's armor looked bulky instead of feminine-shaped like in the anime. Others could have mistaken her for a man if it wasn't for her voice.");
        facts.add("Although Albedo is a succubus, she still happens to be a virgin, indicating from the fact that she wasn't able to meet the requirements in riding her bicorn.");
        facts.add("Among the Floor Guardians and NPCs, Albedo is the only one who harbors hateful thoughts toward their creators including her own, but Momonga.");
        facts.add("Unlike in the Web Novel, Maruyama states that he had included Albedo into the Light Novel for reasons, so Ainz could be able to freely move around outside the Great Tomb of Nazarick.");
        facts.add("Every time he saw Albedo, and every time she declared her love for him, Ainz was reminded of the mistake he made in altering Albedo's settings.");

        return facts.get((int) (Math.random() * facts.size()));
    }
}
