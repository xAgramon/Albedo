package me.agramon.albedo.commands.fun;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.duncte123.botcommons.web.ContentType;
import me.duncte123.botcommons.web.WebParserUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.menudocs.paste.PasteClient;
import org.menudocs.paste.PasteClientBuilder;

import java.util.function.Consumer;

public class Paste extends Command {
    private static final String HASTE_SERVER = "https://hasteb.in/";

    public Paste() {
        super.name = "paste";
        super.category = new Category("Fun");
        super.help = "Creates a hastebin";
        super.cooldown = 10;
        super.aliases = new String[]{"haste"};
        super.arguments = "<message>";
    }

    private final PasteClient client = new PasteClientBuilder()
            .setUserAgent("Albedo")
            .setDefaultExpiry("10m")
            .build();

    @Override
    protected void execute(CommandEvent e) {
        if (e.getArgs().isEmpty()) {
            e.reply("The correct usage is >paste <message>");
            return;
        }

        final String invoke = this.getName();
        final String contentRaw = e.getMessage().getContentRaw();
        final int index = contentRaw.indexOf(invoke) + invoke.length();
        final String body = contentRaw.substring(index).trim();

        this.createPaste(body, (text) -> e.getChannel().sendMessage(text).queue());
    }

    private void createPaste(String text, Consumer<String> callback) {
        Request request = WebUtils.defaultRequest()
                .post(RequestBody.create(text.getBytes()))
                .addHeader("Content-Type", ContentType.TEXT_PLAIN.getType())
                .url(HASTE_SERVER + "documents")
                .build();

        WebUtils.ins.prepareRaw(request, (r) -> WebParserUtils.toJSONObject(r, new ObjectMapper())).async(
                (json) -> {
                    String key = json.get("key").asText();

                    callback.accept(HASTE_SERVER + key);
                },
                (e) -> callback.accept("Error: " + e.getMessage())
        );
    }
}
