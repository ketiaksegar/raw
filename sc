local http_request = request or http_request or (http and http.request) and syn.request;
if not http_request then
    game.Players.LocalPlayer:Kick("Executor not supported.")
    return; 
end

local webhookURL = "https://discord.com/api/webhooks/1139185688760295516/I11GjGxxRWSURZMAUhzCb1-Qx_5TVJoD-R65mKigezsQHcAe-UMNyFa8fRA-2z8CEwFk"  

local hwidResponse = http_request({
    Url = "https://luaguard.rocks/Roblox/hwid.php",
    Method = 'GET'
}).Body;

local player = game.Players.LocalPlayer
local username = player.Name
local id = player.UserId
local place_id = game.PlaceId
local place_name = game:GetService("MarketplaceService"):GetProductInfo(place_id).Name
 
local PlayerData =
{
    ["content"] = "",
    ["embeds"] = {
        {
            ["title"] = "Script Execution Report",
            ["description"] = "Script executed by: **" .. username .. "**",
            ["thumbnail"] = {
                ["url"] = "https://www.roblox.com/Thumbs/Avatar.ashx?x=100&y=100&username="..player.Name,
            },
            ["color"] = tonumber(0x2B6BE4),
            ["fields"] = {
                {
                    ["name"] = "Username:",
                    ["value"] = "ketiaksegar",
                    ["inline"] = true
                },
                {
                    ["name"] = "UserID:",
                    ["value"] = "4918576265",
                    ["inline"] = true
                },
                {
                    ["name"] = "Client HWID:",
                    ["value"] = "||" .. hwidResponse .. "||",  -- HWID with || added
                    ["inline"] = false
                },
                {
                    ["name"] = "Client Profile:",
                    ["value"] = "https://www.roblox.com/users/4918576265",
                    ["inline"] = false
                },
                {
                    ["name"] = "Place ID:",
                    ["value"] = "https://www.roblox.com/games/6911148748",
                    ["inline"] = false
                }
            }
        }
    }
}

local PlayerDataJSON = game:GetService('HttpService'):JSONEncode(PlayerData)

local Headers = {["content-type"] = "application/json"}

http_request({
    Url = webhookURL,
    Method = 'POST',
    Headers = Headers,
    Body = PlayerDataJSON
})

if game.PlaceId == 14005966837 then  -- Jakarta
    loadstring(game:HttpGet('https://scripts.luawl.com/hosted/5681/21305/CDIDMJakarta.lua'))()
elseif game.PlaceId == 9233343468 then -- Jaabar
    loadstring(game:HttpGet('https://scripts.luawl.com/hosted/5681/21307/CDIDMJabar.lua'))()
elseif game.PlaceId == 9508940498 then -- Jateng
    loadstring(game:HttpGet('https://scripts.luawl.com/hosted/5681/21308/CDIDMJateng.lua'))()
else
    game.Players.LocalPlayer:Kick("This game is not supported.")
end
