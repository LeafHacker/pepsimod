/*
 * Adapted from the Wizardry License
 *
 * Copyright (c) 2017 Team Pepsi
 *
 * Permission is hereby granted to any persons and/or organizations using this software to copy, modify, merge, publish, and distribute it.
 * Said persons and/or organizations are not allowed to use the software or any derivatives of the work for commercial use or any other means to generate income, nor are they allowed to claim this software as their own.
 *
 * The persons and/or organizations are also disallowed from sub-licensing and/or trademarking this software without explicit permission from Team Pepsi.
 *
 * Any persons and/or organizations using this software must disclose their source code and have it publicly available, include this license, provide sufficient credit to the original authors of the project (IE: Team Pepsi), as well as provide a link to the original project.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package net.daporkchop.pepsimod.util.config.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.daporkchop.pepsimod.util.PepsiUtils;
import net.daporkchop.pepsimod.util.config.IConfigTranslator;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.Iterator;

public class XrayTranslator implements IConfigTranslator {
    public static final XrayTranslator INSTANCE = new XrayTranslator();
    public ArrayList<Integer> target_blocks = new ArrayList<>();

    private XrayTranslator() {

    }

    public void encode(JsonObject json) {
        JsonArray array = new JsonArray();
        for (Integer integer : target_blocks) {
            array.add(new JsonPrimitive(integer));
        }
        json.add("targetBlocks", array);
    }

    public void decode(String fieldName, JsonObject json) {
        JsonArray array = getArray(json, "targetBlocks", new JsonArray());
        Iterator<JsonElement> iterator = array.iterator();
        while (iterator.hasNext()) {
            target_blocks.add(iterator.next().getAsInt());
        }
    }

    public String name() {
        return "xray";
    }

    public boolean isTargeted(Block block) {
        int id = PepsiUtils.getBlockId(block);
        for (Integer i : target_blocks) {
            if (i == id) {
                return true;
            }
        }

        return false;
    }
}
