package me.agramon.albedo.commands.art;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class artprompt extends Command {
    public artprompt() {
        super.name = "artprompt";
        super.help = "Gives you a random art prompt to draw";
        super.cooldown = 3;
        super.category = new Category("Art");
        super.aliases = new String[] {"aab", "ap"};
    }

    @Override
    protected void execute(CommandEvent e) {
        String thing[] = {"Shadow", "Dragon", "Knight", "Wizard", "Mage", "Rogue", "Witch", "Hunter", "Thief", "Warrior", "Soldier", "Assassin", "Magician", "Sorcerer", "Saint", "Prophet", "Wolf", "Monster", "Lion", "Creature", "Demon", "Angel", "World", "Star", "King", "Queen", "Prince", "Princess", "Lord", "Lady", "God", "Goddess", "Heart", "Eye", "Hand", "Moon", "Sword", "City", "Wheel", "Rune", "Empire", "Ash", "Bone", "War", "Dream", "Storm", "Gate", "Wind", "Rain", "Blade", "Apprentice", "Maid", "Servant", "Teacher", "Man", "Woman", "Tree", "Throne", "Desert", "Daylight", "Ghost", "Crystal", "Mountain", "Glacier", "Sea", "Soul", "Mind", "Magic", "Shard", "Guardian", "Snake", "Army", "Ruin"};
        String end[] = {"of Night", "of Light", "of Day", "of Darkness", "of Autumn", "of Winter", "of Summer", "of Spring", "of Shadows", "of Dawn", "of Dusk", "of Twilight", "of Desire", "of Despair", "of Doom", "of Fire", "of Eternity", "of Fortune", "of Gold", "of Blood", "of Hope", "of Ice", "of Infinity", "of All", "of Sorrow", "of Power", "of Stone", "of the King", "of the Queen", "of the Forsaken", "of the Land", "of the Night", "of the Skull", "of the Stars", "of the Sky", "of the Wind", "of the Void", "of the End", "of Time", "of Tomorrow", "of Whispers", "of Yesterday", "of Old"};

        String prompt = "Your art prompt is: " + thing[(int)(Math.random() * thing.length)] + " " + end[(int)(Math.random() * end.length)];
        EmbedBuilder eb = new EmbedBuilder()
                .setColor(Color.MAGENTA)
                .setDescription(prompt);
        e.reply(eb.build());
    }
}
